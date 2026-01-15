package com.ddf.group.purchase.core.login;

import cn.hutool.core.collection.CollUtil;
import com.ddf.boot.common.authentication.model.AuthenticateToken;
import com.ddf.boot.common.authentication.model.UserClaim;
import com.ddf.boot.common.authentication.util.TokenUtil;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.group.purchase.api.enume.LoginTypeEnum;
import com.ddf.group.purchase.api.request.user.LoginRequest;
import com.ddf.group.purchase.api.response.common.UserLoginResponse;
import com.ddf.group.purchase.core.converter.UserConvert;
import com.ddf.group.purchase.core.exception.ExceptionCode;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>登录策略上下文</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/23 23:36
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class LoginStrategyContext implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Map<LoginTypeEnum, LoginStrategy> loginStrategyMap = new HashMap<>();


    @PostConstruct
    public void init() {
        final Map<String, LoginStrategy> strategyMap = applicationContext.getBeansOfType(LoginStrategy.class);
        if (CollUtil.isEmpty(strategyMap)) {
            return;
        }
        strategyMap.forEach((beanName, clazz) -> {
            loginStrategyMap.put(clazz.getLoginType(), clazz);
        });
    }

    /**
     * 执行登录
     *
     * @param loginRequest
     * @return
     */
    public UserLoginResponse login(LoginRequest loginRequest) {
        final LoginTypeEnum type = loginRequest.getLoginType();
        final LoginStrategy loginStrategy = loginStrategyMap.get(type);
        PreconditionUtil.checkArgument(Objects.nonNull(loginStrategy), ExceptionCode.LOGIN_STRATEGY_MAPPING_ERROR);
        // 校验通过后返回的用户信息
        final UserInfo userInfo = loginStrategy.login(loginRequest);

        // 生成token
        final UserClaim userClaim = UserConvert.INSTANCE.convert(userInfo);
        final AuthenticateToken authenticateToken = TokenUtil.createToken(userClaim);
        return UserLoginResponse.builder()
                .token(authenticateToken.getToken())
                .build();
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }
}
