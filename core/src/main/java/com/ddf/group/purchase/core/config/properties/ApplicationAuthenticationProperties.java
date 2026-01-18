package com.ddf.group.purchase.core.config.properties;

import com.ddf.boot.common.authentication.config.AuthenticationProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 认证模块相关配置类, 主要是为了集成配置中心
 *
 * @author dongfang.ding
 * @date 2019-12-07 16:45
 */
@Data
@NoArgsConstructor
@Component
public class ApplicationAuthenticationProperties extends AuthenticationProperties {

}
