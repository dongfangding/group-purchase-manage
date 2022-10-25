package com.ddf.group.purchase.core.service.impl;

import com.ddf.boot.common.api.util.DateUtils;
import com.ddf.group.purchase.core.mapper.ext.UserInfoExtMapper;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import com.ddf.group.purchase.core.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>用户信息</p >
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
    @Override
    public UserInfo registerUserInfo(String mobile, String password, String avatarUrl) {
        final UserInfo userInfo = new UserInfo();
        userInfo.setMobile(mobile);
        userInfo.setNickname(mobile);
        userInfo.setPassword(password);
        userInfo.setAvatarUrl(avatarUrl);
        userInfo.setCtime(DateUtils.currentTimeSeconds());
        userInfoExtMapper.insert(userInfo);
        return userInfo;
    }

}
