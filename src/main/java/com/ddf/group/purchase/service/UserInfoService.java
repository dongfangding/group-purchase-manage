package com.ddf.group.purchase.service;

import com.ddf.group.purchase.model.request.UserRegistryRequest;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/15 20:54
 */
public interface UserInfoService {

    /**
     * 注册
     *
     * @param request
     */
    void registry(UserRegistryRequest request);
}
