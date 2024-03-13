package icu.leshine.leoj.judge.codesandbox;

import icu.leshine.leoj.judge.codesandbox.entity.ExecuteCodeRequest;
import icu.leshine.leoj.judge.codesandbox.entity.ExecuteCodeResponse;

/**
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-13-20:34
 */
public interface CodeSandBox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);

}
