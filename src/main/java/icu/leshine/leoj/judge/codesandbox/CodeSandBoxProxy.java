package icu.leshine.leoj.judge.codesandbox;

import icu.leshine.leoj.judge.codesandbox.entity.ExecuteCodeRequest;
import icu.leshine.leoj.judge.codesandbox.entity.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 代理代码沙箱的代理类
 *
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-14-11:20
 */
@Slf4j
public class CodeSandBoxProxy implements CodeSandBox{

    private CodeSandBox codeSandBox;

    public CodeSandBoxProxy(CodeSandBox codeSandBox) {
        this.codeSandBox = codeSandBox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        // 增强代码沙箱的能力
        log.info("题目提交信息：" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        log.info("题目响应信息：" + executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
