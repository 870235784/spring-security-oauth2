package com.tca.security.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * @author zhoua
 * @Date 2020/5/25
 */
@Configuration
public class TokenConfig {

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 读取 oauth2.jks 文件中的私钥, 第2个参数是口令 oauth2
        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(new ClassPathResource("oauth2.jks"),
                "oauth2".toCharArray());
        // 别名 oauth2
        jwtAccessTokenConverter.setKeyPair(keyFactory.getKeyPair("oauth2"));
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

}
