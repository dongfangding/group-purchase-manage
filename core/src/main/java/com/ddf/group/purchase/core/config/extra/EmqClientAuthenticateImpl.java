package com.ddf.group.purchase.core.config.extra;

import com.ddf.boot.common.api.model.authentication.AuthenticateCheckResult;
import com.ddf.boot.common.core.authentication.TokenUtil;
import com.ddf.common.boot.mqtt.extra.EmqClientAuthenticate;
import com.ddf.common.boot.mqtt.model.request.emq.EmqAuthenticateRequest;
import com.ddf.common.boot.mqtt.model.response.emq.EmqClientAuthenticateResponse;
import org.springframework.stereotype.Component;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/28 18:10
 */
@Component
public class EmqClientAuthenticateImpl implements EmqClientAuthenticate {

    @Override
    public EmqClientAuthenticateResponse authenticate(EmqAuthenticateRequest request) {
        final EmqClientAuthenticateResponse response = new EmqClientAuthenticateResponse();
        response.setResult(Boolean.TRUE);
        response.setMsg("连接成功");
        try {
            final AuthenticateCheckResult result = TokenUtil.checkToken(request.getPassword());
        } catch (Exception e) {
            response.setMsg(e.getMessage());
            response.setResult(Boolean.FALSE);
        }
        return response;
    }
}
