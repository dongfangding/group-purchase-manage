package com.ddf.group.purchase.core.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ddf.group.purchase.core.mapper.ext.UserInfoExtMapper;
import com.ddf.group.purchase.core.model.cqrs.user.CompleteUserInfoCommand;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import java.util.List;
import java.util.Objects;
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
     * 根据userId获取用户信息
     *
     * @param userId
     * @return
     */
    public UserInfo getById(Long userId) {
        return userInfoExtMapper.selectById(userId);
    }

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

    /**
     * 完善用户信息相关的更新
     *
     * @param command
     * @return
     */
    public int completeUserInfo(CompleteUserInfoCommand command) {
        final LambdaUpdateWrapper<UserInfo> wrapper = Wrappers.lambdaUpdate();
        if (Objects.nonNull(command.getCommunityId())) {
            wrapper.set(UserInfo::getCommunityId, command.getCommunityId());
        }
        if (StrUtil.isNotBlank(command.getBuildingNo())) {
            wrapper.set(UserInfo::getBuildingNo, command.getBuildingNo());
        }
        if (StrUtil.isNotBlank(command.getRoomNo())) {
            wrapper.set(UserInfo::getRoomNo, command.getRoomNo());
        }
        if (Objects.nonNull(command.getEmail())) {
            wrapper.set(UserInfo::getEmail, command.getEmail());
        }
        if (Objects.nonNull(command.getNickname())) {
            wrapper.set(UserInfo::getNickname, command.getNickname());
        }
        if (Objects.nonNull(command.getAvatarUrl())) {
            wrapper.set(UserInfo::getAvatarUrl, command.getAvatarUrl());
        }
        if (Objects.nonNull(command.getAvatarThumbUrl())) {
            wrapper.set(UserInfo::getAvatarThumbUrl, command.getAvatarThumbUrl());
        }
        if (Objects.nonNull(command.getEmailVerified())) {
            wrapper.set(UserInfo::getEmailVerified, command.getEmailVerified());
        }
        wrapper.eq(UserInfo::getId, command.getId());
        return userInfoExtMapper.update(null, wrapper);
    }

    /**
     * 验证邮箱状态
     *
     * @param userId
     * @param email
     * @return
     */
    public int verifiedEmail(Long userId, String email) {
        final LambdaUpdateWrapper<UserInfo> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(UserInfo::getId, userId);
        wrapper.eq(UserInfo::getEmail, email);
        wrapper.eq(UserInfo::getEmailVerified, Boolean.FALSE);
        wrapper.set(UserInfo::getEmailVerified, Boolean.TRUE);
        return userInfoExtMapper.update(null, wrapper);
    }

    /**
     * 根据住址查询用户，一个住址可能有多个用户
     *
     * @param communityId
     * @param buildingNo
     * @param roomNo
     * @return
     */
    public List<UserInfo> getByBuildingAndRoomNo(Integer communityId, String buildingNo, String roomNo) {
        final LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserInfo::getCommunityId, communityId)
                .eq(UserInfo::getBuildingNo, buildingNo)
                .eq(UserInfo::getRoomNo, roomNo);
        return userInfoExtMapper.selectList(wrapper);
    }
}
