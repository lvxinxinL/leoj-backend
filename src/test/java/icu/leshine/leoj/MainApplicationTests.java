package icu.leshine.leoj;

import icu.leshine.leoj.config.CorsConfig;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 主类测试
 */
@SpringBootTest
class MainApplicationTests {

    @Resource
    private CorsConfig corsConfig;

    @Test
    void contextLoads() {
        System.out.println(corsConfig);
    }

}
