package icu.leshine.leoj.judge.codesandbox.impl;

import icu.leshine.leoj.judge.codesandbox.CodeSandBox;
import icu.leshine.leoj.judge.codesandbox.entity.ExecuteCodeRequest;
import icu.leshine.leoj.judge.codesandbox.entity.ExecuteCodeResponse;

/**
 * 示例代码沙箱
 *
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-13-20:42
 */
public class ExampleCodeSandBox implements CodeSandBox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("示例代码沙箱");
        return null;
    }
}
