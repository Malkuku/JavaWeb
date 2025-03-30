package com.itheima;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;

public class UserServiceTest {
    @Test
    public void testGetAge(){
        UserService userService = new UserService();
        Integer age = userService.getAge("100000200010011011");
        System.out.println(age);
    }

    @Test
    public void testGenderWithAssert(){
        UserService userService = new UserService();
        String gender = userService.getGender("100000200010011011");

        Assertions.assertEquals("男",gender,"err1");

        //Assertions.assertThrows(NullPointerException.class,()->{
        //    userService.getGender(null);
        //});
        System.out.println(gender);
    }

    @DisplayName("测试用户性别")
    @ParameterizedTest
    @ValueSource(strings = {"100000200010011011","100000200010011021","100000200010011041"})
    public void testGender(String idCard){
        UserService userService = new UserService();
        String gender = userService.getGender(idCard);
        System.out.println(gender);
    }
}

