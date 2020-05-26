package com.tca.security.resource.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * 资源服务器配置
 * @author zhoua
 * @Date 2020/5/26
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    /**
     * 资源服务器id
     */
    @Value("${resource.id}")
    private String resourceId;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId)
                .tokenServices(tokenServices());
    }

    /**
     * 配置token校验Service
     * @return
     */
    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setClientId("app_tca");
        remoteTokenServices.setClientSecret("123456");
        // 配置认证服务器 token校验, 一定要加上http://
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:8090/oauth/check_token");
        return remoteTokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            // 禁用session
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // 配置访问权限
            .authorizeRequests()
            .antMatchers("/**").access("#oauth2.hasScope('all')");

    }
}
