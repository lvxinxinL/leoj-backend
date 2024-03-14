package icu.leshine.leoj.judge.codesandbox.impl;
import java.util.Arrays;
import java.util.List;

import icu.leshine.leoj.judge.codesandbox.CodeSandBox;
import icu.leshine.leoj.judge.codesandbox.entity.ExecuteCodeRequest;
import icu.leshine.leoj.judge.codesandbox.entity.ExecuteCodeResponse;
import icu.leshine.leoj.model.dto.questionsubmit.JudgeInfo;
import icu.leshine.leoj.model.enums.JudgeInfoMessageEnum;
import icu.leshine.leoj.model.enums.QuestionSubmitStatusEnum;

/**
 * 示例代码沙箱（仅为了跑通业务）
 *
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-13-20:42
 */
public class ExampleCodeSandBox implements CodeSandBox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        List<String> outputList = Arrays.asList("新的输出用例");
        executeCodeResponse.setOutputList(outputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        System.out.println("示例代码沙箱");
        return executeCodeResponse;
    }
}
