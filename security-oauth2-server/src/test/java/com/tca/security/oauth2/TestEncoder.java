package com.tca.security.oauth2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhoua
 * @Date 2020/5/25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEncoder {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncode() {
        System.out.println(passwordEncoder.encode("123456"));
    }
}
