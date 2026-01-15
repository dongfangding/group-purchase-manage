package com.ddf.group.purchase.core.repository;

import com.ddf.group.purchase.core.mapper.ext.UserInfoExtMapper;
import com.ddf.group.purchase.core.model.cqrs.user.CompleteUserInfoCommand;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
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
        return userInfoExtMapper.selectByMobile(mobile);
    }


    /**
     * 根据邮箱查询用户列表， 存在多个，是因为可能邮箱都未认证
     *
     * @param email
     * @return
     */
    public List<UserInfo> listUserByEmail(String email) {
        return userInfoExtMapper.selectByEmail(email);
    }

    /**
     * 根据已认证的邮箱查询用户
     *
     * @param email
     * @return
     */
    public UserInfo getUserByVerifiedEmail(String email) {
        return userInfoExtMapper.selectByVerifiedEmail(email);
    }

    /**
     * 根据手机号查询用户
     *
     * @param mobile
     * @return
     */
    public boolean exitsByMobile(String mobile) {
        return userInfoExtMapper.existsByMobile(mobile) > 0;
    }


    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    public boolean exitsByEmail(String email) {
        return userInfoExtMapper.existsByEmail(email) > 0;
    }

    /**
     * 完善用户信息相关的更新
     *
     * @param command
     * @return
     */
    public int completeUserInfo(CompleteUserInfoCommand command) {
        return userInfoExtMapper.completeUserInfo(command);
    }

    /**
     * 验证邮箱状态
     *
     * @param userId
     * @param email
     * @return
     */
    public int verifiedEmail(Long userId, String email) {
        return userInfoExtMapper.verifiedEmail(userId, email);
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
        return userInfoExtMapper.selectByBuildingAndRoomNo(communityId, buildingNo, roomNo);
    }

    /**
     * 根据用户id集合查询用户对应的列表信息
     *
     * @param uidList
     * @return
     */
    public Map<Long, UserInfo> mapListUsers(Set<Long> uidList) {
        final List<UserInfo> users = userInfoExtMapper.selectByIds(uidList);
        return users.stream().collect(Collectors.toMap(UserInfo::getId, Function.identity()));
    }

    /**
     * 插入用户
     *
     * @param userInfo
     * @return
     */
    public int insert(UserInfo userInfo) {
        return userInfoExtMapper.insert(userInfo);
    }

    /**
     * 更新用户
     *
     * @param userInfo
     * @return
     */
    public int update(UserInfo userInfo) {
        return userInfoExtMapper.updateById(userInfo);
    }
}
