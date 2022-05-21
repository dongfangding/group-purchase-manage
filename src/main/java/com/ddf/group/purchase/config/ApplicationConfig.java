package com.ddf.group.purchase.config;

import com.ddf.boot.common.core.encode.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/21 19:23
 */
@Configuration
public class ApplicationConfig {

    /**
     * 随机散列密码验证器
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
