package icu.leshine.leoj.judge.strategy.impl;

import cn.hutool.json.JSONUtil;
import icu.leshine.leoj.judge.strategy.JudgeContext;
import icu.leshine.leoj.judge.strategy.JudgeStrategyService;
import icu.leshine.leoj.model.dto.question.JudgeCase;
import icu.leshine.leoj.model.dto.question.JudgeConfig;
import icu.leshine.leoj.model.dto.questionsubmit.JudgeInfo;
import icu.leshine.leoj.model.entity.Question;
import icu.leshine.leoj.model.entity.QuestionSubmit;
import icu.leshine.leoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

/**
 * Java 程序判题策略
 *
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-14-14:58
 */
public class JavaLangJudgeStrategy implements JudgeStrategyService {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        Question question = judgeContext.getQuestion();
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();

        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;

        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMemory(judgeInfo.getMemory());
        judgeInfoResponse.setTime(judgeInfo.getTime());

        // 4.1 判断沙箱输出结果的数量和预期的输出结果数量是否一致
        if (outputList.size() != inputList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        // 4.2 依次判断每一组输出是否符合预期
        for (int i = 0; i < outputList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (!judgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }

        // 4.3 判断运行时间、运行内存是否符合题目要求
        Long time = judgeInfo.getTime();
        Long memory = judgeInfo.getMemory();
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Long expectedTimeLimit = judgeConfig.getTimeLimit();
        Long expectedMemoryLimit = judgeConfig.getMemoryLimit();
        // 假设 Java 程序需要额外执行 5s
        final long JAVA_PROGRAM_EXTRA_TIME = 5000L;
        if (time - JAVA_PROGRAM_EXTRA_TIME > expectedTimeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        if (memory > expectedMemoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());// ACCEPTED
        return judgeInfoResponse;
    }
}
