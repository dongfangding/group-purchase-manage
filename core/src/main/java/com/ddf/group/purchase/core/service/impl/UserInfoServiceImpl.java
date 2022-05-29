package com.ddf.group.purchase.core.service.impl;

import com.ddf.group.purchase.core.mapper.ext.UserInfoExtMapper;
import com.ddf.group.purchase.core.repository.UserInfoRepository;
import com.ddf.group.purchase.core.service.UserInfoService;
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

}
