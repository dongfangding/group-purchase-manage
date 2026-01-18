package com.ddf.group.purchase.core.application.impl;

import cn.hutool.core.util.StrUtil;
import com.ddf.boot.common.authentication.util.UserContextUtil;
import com.ddf.boot.common.core.encode.BCryptPasswordEncoder;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.group.purchase.api.request.common.SmsCodeVerifyRequest;
import com.ddf.group.purchase.api.request.user.CompleteUserInfoRequest;
import com.ddf.group.purchase.api.request.user.ModifyPasswordRequest;
import com.ddf.group.purchase.api.request.user.UserAddressRequest;
import com.ddf.group.purchase.api.request.user.UserRegistryRequest;
import com.ddf.group.purchase.api.response.user.PersonalInfoResponse;
import com.ddf.group.purchase.api.response.user.UserAddressResponse;
import com.ddf.group.purchase.core.application.UserApplicationService;
import com.ddf.group.purchase.core.client.MailClient;
import com.ddf.group.purchase.core.client.UserClient;
import com.ddf.group.purchase.core.config.properties.ApplicationProperties;
import com.ddf.group.purchase.core.converter.UserAddressConvert;
import com.ddf.group.purchase.core.converter.UserConvert;
import com.ddf.group.purchase.core.exception.ExceptionCode;
import com.ddf.group.purchase.core.helper.CommonHelper;
import com.ddf.group.purchase.core.mapper.UserAddressMapper;
import com.ddf.group.purchase.core.mapper.UserInfoMapper;
import com.ddf.group.purchase.core.model.cqrs.user.CompleteUserInfoCommand;
import com.ddf.group.purchase.core.model.entity.CommunityBase;
import com.ddf.group.purchase.core.model.entity.UserAddress;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import com.ddf.group.purchase.core.repository.CommunityBaseRepository;
import com.ddf.group.purchase.core.repository.UserAddressRepository;
import com.ddf.group.purchase.core.repository.UserInfoRepository;
import com.ddf.group.purchase.core.service.UserInfoService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>用户业务层实现</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/29 22:26
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserInfoService userInfoService;
    private final UserInfoMapper userInfoMapper;
    private final CommunityBaseRepository communityBaseRepository;
    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CommonHelper commonHelper;
    private final UserClient userClient;
    private final MailClient mailClient;
    private final UserAddressMapper userAddressMapper;
    private final UserAddressRepository userAddressRepository;
    private final ApplicationProperties applicationProperties;

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
                .uuid(request.getUuid())
                .build();
        commonHelper.verifySmsCode(verifyRequest);
        userInfoService.registerUserInfo(request.getMobile(), bCryptPasswordEncoder.encode(request.getPassword()),
                applicationProperties.getDefaultAvatarUrl());
    }

    @Override
    public PersonalInfoResponse completeInfo(CompleteUserInfoRequest request) {
        if (StrUtil.isAllEmpty(request.getEmail(), request.getNickname(), request.getAvatarUrl(), request.getAvatarThumbUrl())) {
            return personalInfo();
        }
        final UserInfo userInfo = userClient.currentUserInfo();
        final CompleteUserInfoCommand command = CompleteUserInfoCommand.builder()
                .id(userInfo.getId())
                .communityId(request.getCommunityId())
                .buildingNo(request.getBuildingNo())
                .roomNo(request.getRoomNo())
                .nickname(request.getNickname())
                .avatarUrl(request.getAvatarUrl())
                .avatarThumbUrl(request.getAvatarThumbUrl())
                .build();
        // 邮件未认证或者更改邮件重新发送激活邮件
        if (StrUtil.isNotBlank(request.getEmail()) &&
                (!Objects.equals(request.getEmail(), userInfo.getEmail()) || !userInfo.getEmailVerified())) {
            PreconditionUtil.checkArgument(Objects.isNull(userInfoRepository.getUserByVerifiedEmail(request.getEmail())),
                    ExceptionCode.EMAIL_HAD_BINDING_OTHERS);
            userInfo.setTempEmail(request.getEmail());
            mailClient.sendEmailActive(userInfo.getId(), request.getEmail());
            // 如果是更改了邮件，则重新设置状态为未认证
            if (!Objects.equals(request.getEmail(), userInfo.getEmail())) {
                command.setEmailVerified(Boolean.FALSE);
            }
            command.setEmail(request.getEmail());
        }
        userInfoRepository.completeUserInfo(command);
        return personalInfo();
    }

    @Override
    public PersonalInfoResponse personalInfo() {
        final UserInfo userInfo = userClient.currentUserInfo();
        CommunityBase communityBase = null;
        if (Objects.nonNull(userInfo.getCommunityId())) {
            communityBase = communityBaseRepository.getById(userInfo.getCommunityId().longValue());
        }
        return UserConvert.INSTANCE.convertToPersonalInfoResponse(userInfo, communityBase);
    }

    @Override
    public void modifyPassword(ModifyPasswordRequest request) {
        final UserInfo userInfo = userInfoRepository.getById(UserContextUtil.getLongUserId());
        if (Objects.isNull(userInfo)) {
            return;
        }
        // 短信验证码校验
        final SmsCodeVerifyRequest verifyRequest = SmsCodeVerifyRequest.builder()
                .mobile(userInfo.getMobile())
                .mobileCode(request.getVerifyCode())
                .uuid(request.getUuid())
                .build();
        commonHelper.verifySmsCode(verifyRequest);
        userInfo.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        userInfoMapper.updateById(userInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addressCommand(UserAddressRequest request) {
        final UserAddress userAddress = UserAddressConvert.INSTANCE.convert(request);
        final Long userId = UserContextUtil.getLongUserId();
        // 取消其它数据的默认收货设置
        userAddressRepository.cancelUserDefaultAddress(userId);
        if (Objects.isNull(userAddress.getId())) {
            userAddress.setUid(userId);
            userAddressMapper.insert(userAddress);
        } else {
            userAddressMapper.updateById(userAddress);
        }
    }

    @Override
    public List<UserAddressResponse> listUserAddress(Long uid) {
        return UserAddressConvert.INSTANCE.convert(userAddressRepository.listUserAddress(uid));
    }

    @Override
    public int deleteUserAddress(Long id, Long userId) {
        return userAddressRepository.deleteUserAddress(id, userId);
    }


}
