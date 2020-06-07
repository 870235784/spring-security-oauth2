package com.tca.security.oauth2.clientb.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zhoua
 * @Date 2020/5/31
 */
@EnableOAuth2Sso
@Configuration
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            // 不需认证可以访问/
            .antMatchers("/").permitAll()
            // 其他请求 均需要认证
            .anyRequest().authenticated()
        .and()
            // 当前应用退出
            .logout()
            // 当前退出成功后，跳转到认证服务器退出
            .logoutSuccessUrl("http://localhost:7001/auth/logout")
            .and()
            .csrf().disable();
    }
}
