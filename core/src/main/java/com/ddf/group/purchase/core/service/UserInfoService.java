package com.ddf.group.purchase.core.service;

import com.ddf.group.purchase.core.model.request.user.CompleteUserInfoRequest;
import com.ddf.group.purchase.core.model.request.user.UserRegistryRequest;

/**
 * <p>用户信息</p >
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

    /**
     * 完善用户信息
     *
     * @param request
     */
    void completeInfo(CompleteUserInfoRequest request);
}
