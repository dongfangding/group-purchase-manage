package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.cqrs.user.CompleteUserInfoCommand;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2026/02/03 23:45
 */
public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    UserInfo selectById(Long id);

    /**
     * 根据手机号查询
     *
     * @param mobile
     * @return
     */
    UserInfo selectByMobile(String mobile);

    /**
     * 根据邮箱查询
     *
     * @param email
     * @return
     */
    List<UserInfo> selectByEmail(String email);

    /**
     * 查询所有
     *
     * @return
     */
    List<UserInfo> selectList();

    /**
     * 根据ID集合查询
     *
     * @param ids
     * @return
     */
    List<UserInfo> selectByIds(@Param("ids") Set<Long> ids);

    /**
     * 检查手机号是否存在
     *
     * @param mobile
     * @return
     */
    int existsByMobile(@Param("mobile") String mobile);

    /**
     * 检查邮箱是否存在
     *
     * @param email
     * @return
     */
    int existsByEmail(@Param("email") String email);

    /**
     * 完善用户信息
     *
     * @param command
     * @return
     */
    int completeUserInfo(@Param("command") CompleteUserInfoCommand command);

    /**
     * 验证邮箱
     *
     * @param userId
     * @param email
     * @return
     */
    int verifiedEmail(@Param("userId") Long userId, @Param("email") String email);

    /**
     * 根据ID更新
     *
     * @param userInfo
     * @return
     */
    int updateById(UserInfo userInfo);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据已认证的邮箱查询用户
     *
     * @param email
     * @return
     */
    UserInfo selectByVerifiedEmail(@Param("email") String email);

    /**
     * 根据住址查询用户
     *
     * @param communityId
     * @param buildingNo
     * @param roomNo
     * @return
     */
    List<UserInfo> selectByBuildingAndRoomNo(@Param("communityId") Integer communityId,
            @Param("buildingNo") String buildingNo, @Param("roomNo") String roomNo);
}