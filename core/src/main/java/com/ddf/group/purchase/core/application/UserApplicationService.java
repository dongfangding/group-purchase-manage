package com.ddf.group.purchase.core.application;

import com.ddf.group.purchase.api.request.user.CompleteUserInfoRequest;
import com.ddf.group.purchase.api.request.user.ModifyPasswordRequest;
import com.ddf.group.purchase.api.request.user.UserAddressRequest;
import com.ddf.group.purchase.api.request.user.UserRegistryRequest;
import com.ddf.group.purchase.api.response.user.PersonalInfoResponse;
import com.ddf.group.purchase.api.response.user.UserAddressResponse;
import java.util.List;

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
    PersonalInfoResponse completeInfo(CompleteUserInfoRequest request);

    /**
     * 个人中心
     *
     * @return
     */
    PersonalInfoResponse personalInfo();

    /**
     * 修改密码
     *
     * @param request
     */
    void modifyPassword(ModifyPasswordRequest request);

    /**
     * 用户收货地址维护
     *
     * @param request
     */
    void addressCommand(UserAddressRequest request);

    /**
     * 查询用户的收货地址
     *
     * @param uid
     * @return
     */
    List<UserAddressResponse> listUserAddress(Long uid);

    /**
     * 删除用户收货地址
     *
     * @param id
     * @param userId
     */
    int deleteUserAddress(Long id, Long userId);
}
