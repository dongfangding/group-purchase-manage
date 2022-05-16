package com.ddf.group.purchase.config.properties;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/15 22:51
 */
@Data
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {

    private List<String> mobileWhiteList;


}
