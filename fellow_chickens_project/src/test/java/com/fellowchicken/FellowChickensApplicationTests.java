package com.fellowchicken;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.fellowchicken.mapper")
class FellowChickensApplicationTests {

    @Test
    void contextLoads() {
    }

}
