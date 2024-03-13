package icu.leshine.leoj.judge.codesandbox;

import icu.leshine.leoj.judge.codesandbox.impl.ExampleCodeSandBox;
import icu.leshine.leoj.judge.codesandbox.impl.RemoteCodeSandBox;
import icu.leshine.leoj.judge.codesandbox.impl.ThirdPartySandBox;

import java.util.Scanner;

/**
 * 代码沙箱工厂
 *
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-13-20:54
 */
public class CodeSandBoxFactory {

    /**
     * 根据用户输入参数按类型创建代码沙箱实例
     *
     * @return
     */
    public static CodeSandBox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandBox();
            case "remote":
                return new RemoteCodeSandBox();
            case "thirdParty":
                return new ThirdPartySandBox();
            default:
                return new ExampleCodeSandBox();
        }
    }
}
