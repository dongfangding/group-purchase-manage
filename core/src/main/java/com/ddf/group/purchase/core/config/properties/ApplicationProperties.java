package com.ddf.group.purchase.core.config.properties;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * <p>应用相关属性类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/28 14:41
 */
@Data
//@NacosPropertySource(dataId = "application-properties", groupId = "dev", autoRefreshed = true, type = ConfigType.PROPERTIES)
@Component
@NacosConfigurationProperties(prefix = "app", dataId = "application-properties", groupId = "dev", type = ConfigType.YAML, autoRefreshed = true)
public class ApplicationProperties {

    /**
     * 邮件激活接口地址
     * // @NacosValue(value = "${mailActiveUrl:}", autoRefreshed = true)
     */
    private String mailActiveUrl;

    /**
     * 短信验证码每日限额数量
     * // @NacosValue(value = "${smsDailyLimit:10}", autoRefreshed = true)
     */
    private Integer smsDailyLimit = 10;

}
