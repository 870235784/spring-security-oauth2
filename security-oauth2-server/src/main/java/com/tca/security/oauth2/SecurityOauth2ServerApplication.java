package com.tca.security.oauth2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhoua
 * @Date 2020/5/24
 */
@SpringBootApplication
@ComponentScan("com.tca")
@MapperScan("com.tca.security.oauth2.web.mapper")
public class SecurityOauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityOauth2ServerApplication.class);
    }
}
