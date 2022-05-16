package com.ddf.group.purchase;

import com.ddf.boot.common.core.logaccess.EnableLogAspect;
import com.ddf.boot.common.limit.repeatable.annotation.EnableRepeatable;
import com.ddf.boot.common.limit.repeatable.validator.RedisRepeatableValidator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/10 23:55
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.ddf.group.purchase.mapper"})
//@EnableJwt()
@EnableLogAspect(slowTime = 3000)
@EnableRepeatable(globalValidator = RedisRepeatableValidator.BEAN_NAME)
//@EnableNacosConfig
public class GroupPurchaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(GroupPurchaseApplication.class);
    }
}
