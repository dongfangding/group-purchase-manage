package com.ddf.group.purchase.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ddf.boot.common.core.encode.BCryptPasswordEncoder;
import com.ddf.boot.common.core.util.DateUtils;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.group.purchase.core.client.MailClient;
import com.ddf.group.purchase.core.client.UserClient;
import com.ddf.group.purchase.core.exception.ExceptionCode;
import com.ddf.group.purchase.core.helper.CommonHelper;
import com.ddf.group.purchase.core.mapper.ext.UserInfoExtMapper;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import com.ddf.group.purchase.core.model.request.common.SmsCodeVerifyRequest;
import com.ddf.group.purchase.core.model.request.user.CompleteUserInfoRequest;
import com.ddf.group.purchase.core.model.request.user.UserRegistryRequest;
import com.ddf.group.purchase.core.repository.UserInfoRepository;
import com.ddf.group.purchase.core.repository.model.CompleteUserInfoCommand;
import com.ddf.group.purchase.core.service.UserInfoService;
import java.util.Objects;
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
    private final UserClient userClient;
    private final MailClient mailClient;

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

        final UserInfo userInfo = new UserInfo();
        userInfo.setCommunityId(request.getCommunityId());
        userInfo.setBuildingNo(request.getBuildingNo());
        userInfo.setRoomNo(request.getRoomNo());
        userInfo.setMobile(request.getMobile());
        userInfo.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userInfo.setCtime(DateUtils.currentTimeSeconds());
        userInfoExtMapper.insert(userInfo);
    }

    @Override
    public void completeInfo(CompleteUserInfoRequest request) {
        if (StrUtil.isAllEmpty(request.getEmail(), request.getNickname(), request.getAvatarUrl(), request.getAvatarThumbUrl())) {
            return;
        }
        final UserInfo userInfo = userClient.currentUserInfo();
        final CompleteUserInfoCommand command = CompleteUserInfoCommand.builder()
                .id(userInfo.getId())
                .nickname(request.getNickname())
                .email(request.getEmail())
                .avatarUrl(request.getAvatarUrl())
                .avatarThumbUrl(request.getAvatarThumbUrl())
                .build();
        // 邮件未认证或者更改邮件重新发送激活邮件
        if (StrUtil.isNotBlank(request.getEmail()) &&
                (!Objects.equals(request.getEmail(), userInfo.getEmail()) || !userInfo.getEmailVerified())) {
            userInfo.setEmail(request.getEmail());
            mailClient.sendEmailActive(request.getEmail());
            // 如果是更改了邮件，则重新设置状态为未认证
            if (!Objects.equals(request.getEmail(), userInfo.getEmail())) {
                command.setEmailVerified(Boolean.FALSE);
            }
        }
        userInfoRepository.completeUserInfo(command);
    }
}
