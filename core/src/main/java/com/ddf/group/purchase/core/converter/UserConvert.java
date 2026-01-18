package com.ddf.group.purchase.core.converter;

import com.ddf.boot.common.api.model.authentication.UserClaim;
import com.ddf.boot.common.mvc.util.WebUtil;
import com.ddf.group.purchase.api.response.user.PersonalInfoResponse;
import com.ddf.group.purchase.core.model.entity.CommunityBase;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * <p>user相关转换类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/24 19:53
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    /**
     * 用户信息转认证用户对象信息
     *
     * @param userInfo
     * @return
     */
    default UserClaim convert(UserInfo userInfo) {
        return UserClaim.builder()
                .userId(userInfo.getId().toString())
                .username(userInfo.getMobile())
                .credit(WebUtil.getUserAgent())
                .remarks(null)
                .detail(null)
                .build();
    }

    /**
     * 用户信息转个人中心用户信息
     *
     * @param userInfo
     * @return
     */
    @Mappings({
            @Mapping(target = "id", source = "userInfo.id")
    })
    PersonalInfoResponse convertToPersonalInfoResponse(UserInfo userInfo, CommunityBase communityBase);
}
