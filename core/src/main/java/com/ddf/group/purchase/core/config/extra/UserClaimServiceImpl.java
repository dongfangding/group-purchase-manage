package com.ddf.group.purchase.core.config.extra;

import com.ddf.boot.common.api.model.authentication.UserClaim;
import com.ddf.boot.common.authentication.interfaces.UserClaimService;
import com.ddf.group.purchase.core.converter.UserConvert;
import com.ddf.group.purchase.core.mapper.UserInfoMapper;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>认证模块用户认证接口</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/26 23:06
 */
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserClaimServiceImpl implements UserClaimService {

    private final UserInfoMapper userInfoMapper;

    @Override
    public void storeRequest(HttpServletRequest request, String host) {

    }

    @Override
    public UserClaim getStoreUserInfo(HttpServletRequest request, UserClaim claim) {
        final UserInfo userInfo = userInfoMapper.selectById(Long.parseLong(claim.getUserId()));
        return UserConvert.INSTANCE.convert(userInfo);
    }
}
