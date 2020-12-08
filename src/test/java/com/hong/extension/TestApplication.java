package com.hong.extension;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: ZhangDeHong
 * @Describe: TODO
 * @Date Create in  10:48 下午 2020/10/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void contextLoad () {
        logger.info("------ok---------");
    }
}
