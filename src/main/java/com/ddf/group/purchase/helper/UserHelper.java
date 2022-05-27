package com.ddf.group.purchase.helper;

import com.ddf.boot.common.authentication.util.UserContextUtil;
import com.ddf.group.purchase.mapper.ext.UserInfoExtMapper;
import com.ddf.group.purchase.model.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>用户帮助类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/27 23:17
 */
@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserHelper {

    private final UserInfoExtMapper userInfoExtMapper;

    /**
     * 当前用户信息对象
     *
     * @return
     */
    public UserInfo currentUserInfo() {
        return userInfoExtMapper.selectById(UserContextUtil.getUserId());
    }
}
