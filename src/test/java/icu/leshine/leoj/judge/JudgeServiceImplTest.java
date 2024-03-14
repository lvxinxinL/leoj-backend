package icu.leshine.leoj.judge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


/**
 * 判题服务测试类
 *
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-14-12:10
 */
@SpringBootTest
class JudgeServiceImplTest {

    @Resource
    private JudgeService judgeService;

    @Test
    void doJudgeTest() {
        long questionSubmitId = 1767819576823250945L;
        judgeService.doJudge(questionSubmitId);
    }

}