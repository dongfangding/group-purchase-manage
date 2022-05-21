package com.ddf.group.purchase.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ddf.group.purchase.mapper.ext.UserInfoExtMapper;
import com.ddf.group.purchase.model.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>用户信息仓储/p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/21 10:53
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserInfoRepository {

    private final UserInfoExtMapper userInfoExtMapper;

    /**
     * 根据手机号查询用户
     *
     * @param mobile
     * @return
     */
    public UserInfo getByMobile(String mobile) {
        final LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserInfo::getMobile, mobile);
        return userInfoExtMapper.selectOne(wrapper);
    }


    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    public UserInfo getByEmail(String email) {
        final LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserInfo::getEmail, email);
        return userInfoExtMapper.selectOne(wrapper);
    }




    /**
     * 根据手机号查询用户
     *
     * @param mobile
     * @return
     */
    public boolean exitsByMobile(String mobile) {
        final LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserInfo::getMobile, mobile);
        return userInfoExtMapper.selectCount(wrapper) > 0;
    }


    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    public boolean exitsByEmail(String email) {
        final LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserInfo::getEmail, email);
        return userInfoExtMapper.selectCount(wrapper) > 0;
    }
}
