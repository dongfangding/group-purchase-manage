package com.ddf.group.purchase.core.application;

import com.ddf.group.purchase.api.request.user.CompleteUserInfoRequest;
import com.ddf.group.purchase.api.request.user.UserRegistryRequest;
import com.ddf.group.purchase.api.response.user.PersonalInfoResponse;

/**
 * <p>用户业务层</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/29 22:25
 */
public interface UserApplicationService {

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

    /**
     * 个人中心
     *
     * @return
     */
    PersonalInfoResponse personalInfo();
}
