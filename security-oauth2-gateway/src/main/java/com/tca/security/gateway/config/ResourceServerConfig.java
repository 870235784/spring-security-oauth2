package com.tca.security.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author zhoua
 * @Date 2020/6/6
 */
@Configuration
public class ResourceServerConfig {


    public static final String RESOURCE_ID = "product-server";

    @Autowired
    private TokenStore tokenStore;

    /**
     * 网关配置认证服务器配置
     */
    @Configuration
    @EnableResourceServer
    public class AuthResourceServerConfig extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID).tokenStore(tokenStore);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // 放行所有请求
            http.authorizeRequests()
                    .anyRequest().permitAll();
        }
    }

    @Configuration
    @EnableResourceServer
    public class ProductResourceServerConfig extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID).tokenStore(tokenStore);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // 访问/product/** 需要客户端有'PRODUCT_API'的权限
            http.authorizeRequests()
                    .antMatchers("/product/**")
                    .access("#oauth2.hasAnyScope('PRODUCT_API')");
        }
    }

}
