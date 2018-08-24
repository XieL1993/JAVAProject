package com.hundsun.house;

import com.hundsun.house.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AppTest {
    @Autowired
    private FileService fileService;

    @Test
    public void test() {
        String test = fileService.test();
        System.out.println(test);
    }
}
