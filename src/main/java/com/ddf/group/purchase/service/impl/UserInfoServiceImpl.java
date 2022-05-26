package com.ddf.group.purchase.service.impl;

import com.ddf.boot.common.core.encode.BCryptPasswordEncoder;
import com.ddf.boot.common.core.util.DateUtils;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.group.purchase.exception.ExceptionCode;
import com.ddf.group.purchase.helper.CommonHelper;
import com.ddf.group.purchase.mapper.ext.UserInfoExtMapper;
import com.ddf.group.purchase.model.entity.UserInfo;
import com.ddf.group.purchase.model.request.common.SmsCodeVerifyRequest;
import com.ddf.group.purchase.model.request.user.UserRegistryRequest;
import com.ddf.group.purchase.repository.UserInfoRepository;
import com.ddf.group.purchase.service.UserInfoService;
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
    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CommonHelper commonHelper;

    /**
     * 注册
     *
     * @param request
     */
    @Override
    public void registry(UserRegistryRequest request) {
        PreconditionUtil.checkArgument(!userInfoRepository.exitsByMobile(request.getMobile()),
                ExceptionCode.MOBILE_IS_USED);
        // 短信验证码校验
        final SmsCodeVerifyRequest verifyRequest = SmsCodeVerifyRequest.builder()
                .mobile(request.getMobile())
                .mobileCode(request.getMobileCode())
                .tokenId(request.getTokenId())
                .build();
        commonHelper.verifySmsCode(verifyRequest);

        final UserInfo userInfo = new UserInfo();
        userInfo.setCommunityId(request.getCommunityId());
        userInfo.setBuildingNo(request.getBuildingNo());
        userInfo.setRoomNo(request.getRoomNo());
        userInfo.setMobile(request.getMobile());
        userInfo.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userInfo.setCtime(DateUtils.currentTimeSeconds());
        userInfoExtMapper.insert(userInfo);
    }
}
