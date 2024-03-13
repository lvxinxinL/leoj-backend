package icu.leshine.leoj.judge.codesandbox;

import icu.leshine.leoj.judge.codesandbox.entity.ExecuteCodeRequest;
import icu.leshine.leoj.judge.codesandbox.impl.ThirdPartySandBox;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * 测试代码沙箱
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-13-20:44
 */
@SpringBootTest
class CodeSandBoxTest {

    @Test
    void codeSandBoxTest() {
        CodeSandBox codeSandBox = new ThirdPartySandBox();// 面向接口/抽象编程
        List<String> inputList = Arrays.asList("1 2", "3 4");
        String code = "int main() {}";
        String language = "java";
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .inputList(inputList)
                .code(code)
                .language(language)
                .build();
        codeSandBox.executeCode(executeCodeRequest);
    }

}