package com.ddf.group.purchase.core;

import com.ddf.boot.common.authentication.annotation.EnableAuthenticate;
import com.ddf.boot.common.limit.ratelimit.annotation.EnableRateLimit;
import com.ddf.boot.common.limit.repeatable.annotation.EnableRepeatable;
import com.ddf.boot.common.limit.repeatable.validator.RedisRepeatableValidator;
import com.ddf.boot.common.mvc.logaccess.EnableLogAspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/10 23:55
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.ddf.group.purchase.core.mapper"})
@EnableAuthenticate
@EnableAsync
@EnableScheduling
@EnableLogAspect(slowTime = 3000)
@EnableRateLimit()
@EnableRepeatable(globalValidator = RedisRepeatableValidator.BEAN_NAME)
//@EnableNacosConfig
public class GroupPurchaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(GroupPurchaseApplication.class);
    }
}
