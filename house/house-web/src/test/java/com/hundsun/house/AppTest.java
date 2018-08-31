package com.hundsun.house;

import com.hundsun.house.bean.User;
import com.hundsun.house.service.FileService;
import com.hundsun.house.service.UserService;
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
    @Autowired
    private UserService userService;


    @Test
    public void test() {
        String test = fileService.test();
        System.out.println(test);
    }

    @Test
    public void test2() {
        User auth = userService.auth("1186654762@qq.com", "222222");
        System.out.println(auth);
    }
}
