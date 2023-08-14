package com;

import com.skycommon.properties.AliOssProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SkyTakeOutFinalApplicationTests {

    @Autowired
    private AliOssProperties aliOssProperties;

    @Test
    void contextLoads() {
        System.out.println(aliOssProperties);
    }

}
