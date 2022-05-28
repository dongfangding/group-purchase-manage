package com.ddf.group.purchase.core.converter;

import com.ddf.boot.common.authentication.model.UserClaim;
import com.ddf.boot.common.core.util.WebUtil;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import org.mapstruct.Mapper;
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
                .lastModifyPasswordTime(null)
                .remarks(null)
                .detail(null)
                .build();
    }
}
