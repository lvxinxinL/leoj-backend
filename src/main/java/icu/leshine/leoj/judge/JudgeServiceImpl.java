package icu.leshine.leoj.judge;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import icu.leshine.leoj.common.ErrorCode;
import icu.leshine.leoj.exception.BusinessException;
import icu.leshine.leoj.judge.codesandbox.CodeSandBox;
import icu.leshine.leoj.judge.codesandbox.CodeSandBoxFactory;
import icu.leshine.leoj.judge.codesandbox.CodeSandBoxProxy;
import icu.leshine.leoj.judge.codesandbox.entity.ExecuteCodeRequest;
import icu.leshine.leoj.judge.codesandbox.entity.ExecuteCodeResponse;
import icu.leshine.leoj.judge.strategy.JudgeManager;
import icu.leshine.leoj.judge.strategy.impl.DefaultJudgeStrategy;
import icu.leshine.leoj.judge.strategy.JudgeContext;
import icu.leshine.leoj.judge.strategy.JudgeStrategyService;
import icu.leshine.leoj.judge.strategy.impl.JavaLangJudgeStrategy;
import icu.leshine.leoj.model.dto.question.JudgeCase;
import icu.leshine.leoj.model.dto.questionsubmit.JudgeInfo;
import icu.leshine.leoj.model.entity.Question;
import icu.leshine.leoj.model.entity.QuestionSubmit;
import icu.leshine.leoj.model.enums.QuestionSubmitStatusEnum;
import icu.leshine.leoj.model.vo.QuestionSubmitVO;
import icu.leshine.leoj.service.QuestionService;
import icu.leshine.leoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 判题服务实现类
 *
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-14-11:49
 */
@Service
public class JudgeServiceImpl implements JudgeService{

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type:example}")
    private String type;// 读取配置文件

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        // 1. 校验题目信息、题目提交信息、题目提交状态
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目提交信息不存在");
        }
        Question question = questionService.getById(questionSubmit.getQuestionId());
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        // 只执行提交状态为等待中的题目，不要重复执行
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题");
        }

        // 2. 校验通过，更新题目提交状态为“判题中”
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新失败");
        }

        // 3. 调用代码沙箱执行代码，获取执行结果
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);// 面向接口/抽象编程
        codeSandBox = new CodeSandBoxProxy(codeSandBox);
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .inputList(inputList)
                .code(code)
                .language(language)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);

        // 4. 使用判题策略，判断沙箱的执行结果是否符合题目答案
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        List<String> outputList = executeCodeResponse.getOutputList();
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        // 让 JudgeManager 选择判题策略
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);//传入封装的上下文对象，执行判题

        // 5. 修改数据库的题目提交信息
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(executeCodeResponse.getStatus());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        boolean updateResult = questionSubmitService.updateById(questionSubmitUpdate);
        if (!updateResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新失败");
        }
        QuestionSubmit questionSubmitResult = questionSubmitService.getById(questionSubmitId);
        return questionSubmitResult;
    }
}
