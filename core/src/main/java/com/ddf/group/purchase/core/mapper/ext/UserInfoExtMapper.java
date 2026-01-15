package com.ddf.group.purchase.core.mapper.ext;

import com.ddf.group.purchase.core.mapper.UserInfoMapper;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import java.util.List;

/**
* <p>用户信息</p >
*
* @author Snowball
* @version 1.0
* @date 2022/05/15 20:49
*/
public interface UserInfoExtMapper extends UserInfoMapper {

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
    List<UserInfo> selectByBuildingAndRoomNo(
            @Param("communityId") Integer communityId,
            @Param("buildingNo") String buildingNo,
            @Param("roomNo") String roomNo);
}
