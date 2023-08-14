package com;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @ClassName SpringTaskTest
 * @Author shuai
 * @create 2023/8/10 10:06
 * @Instruction
 */
public class SpringTaskTest {
    @Test
    public void test(){
        LocalDateTime begin = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        System.out.println(begin);
    }
}
