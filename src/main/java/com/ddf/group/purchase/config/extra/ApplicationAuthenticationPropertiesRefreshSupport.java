package com.ddf.group.purchase.config.extra;

import com.ddf.boot.common.authentication.config.AuthenticationProperties;
import com.ddf.boot.common.authentication.interfaces.AuthenticationPropertiesRefreshSupport;
import com.ddf.group.purchase.config.properties.ApplicationAuthenticationProperties;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/28 20:36
 */
@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class ApplicationAuthenticationPropertiesRefreshSupport implements AuthenticationPropertiesRefreshSupport {

    private final ApplicationAuthenticationProperties applicationAuthenticationProperties;

    @Override
    public String getSecret() {
        return applicationAuthenticationProperties.getSecret();
    }

    @Override
    public String getTokenHeaderName() {
        return applicationAuthenticationProperties.getTokenHeaderName();
    }

    @Override
    public String getTokenPrefix() {
        return applicationAuthenticationProperties.getTokenPrefix();
    }

    @Override
    public String getCreditHeaderName() {
        return applicationAuthenticationProperties.getCreditHeaderName();
    }

    @Override
    public List<String> getIgnores() {
        return applicationAuthenticationProperties.getIgnores();
    }

    @Override
    public Integer getExpiredMinute() {
        return applicationAuthenticationProperties.getExpiredMinute();
    }

    @Override
    public boolean isMock() {
        return applicationAuthenticationProperties.isMock();
    }

    @Override
    public List<String> getMockUserIdList() {
        return applicationAuthenticationProperties.getMockUserIdList();
    }

    @Override
    public AuthenticationProperties.Biz getBiz() {
        final AuthenticationProperties.Biz biz = new AuthenticationProperties.Biz();
        biz.setResetPassword(applicationAuthenticationProperties.getBiz().getResetPassword());
        biz.setWhiteLoginNameList(applicationAuthenticationProperties.getBiz().getWhiteLoginNameList());
        return biz;
    }
}
