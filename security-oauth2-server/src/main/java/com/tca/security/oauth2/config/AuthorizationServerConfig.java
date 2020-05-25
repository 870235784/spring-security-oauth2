package com.tca.security.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 认证服务器配置
 * @author zhoua
 * @Date 2020/5/24
 */
@Configuration
@EnableAuthorizationServer // 认证服务配置
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    private static final String[] GRANT_TYPE = {
            // 授权码
            "authorization_code",
            // 密码
            "password",
            // 简易
            "implicit",
            // 客户端认证
            "client_credentials",
            // token刷新
            "refresh_token"
    };

    /**
     * 配置允许访问当前认证服务器(可申请令牌)的客户端
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 使用内存方式
        clients.inMemory()
                // app_name
                .withClient("app_tca")
                // app_secret 必须加密
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes(GRANT_TYPE)
                .scopes("all")
                .autoApprove(false)
                // 客户端回调地址
                .redirectUris("https://www.baidu.com/");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(userDetailsService);
        endpoints.tokenStore(tokenStore);
        endpoints.authorizationCodeServices(authorizationCodeServices);
    }
}
