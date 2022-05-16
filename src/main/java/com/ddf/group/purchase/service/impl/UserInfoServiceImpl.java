package com.ddf.group.purchase.service.impl;

import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.group.purchase.mapper.ext.UserInfoExtMapper;
import com.ddf.group.purchase.model.request.UserRegistryRequest;
import com.ddf.group.purchase.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/15 22:53
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoExtMapper userInfoExtMapper;

    /**
     * 注册
     *
     * @param request
     */
    @Override
    public void registry(UserRegistryRequest request) {
        PreconditionUtil.requiredParamCheck(request);



    }
}
