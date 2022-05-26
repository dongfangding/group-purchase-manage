package com.ddf.group.purchase.strategy.login;

import cn.hutool.core.collection.CollUtil;
import com.ddf.group.purchase.constants.LoginTypeEnum;
import com.ddf.group.purchase.model.request.user.LoginRequest;
import com.ddf.group.purchase.model.response.UserLoginResponse;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
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
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }
}
