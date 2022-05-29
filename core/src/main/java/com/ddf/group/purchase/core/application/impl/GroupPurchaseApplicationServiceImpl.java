package com.ddf.group.purchase.core.application.impl;

import com.ddf.group.purchase.api.request.group.CreateFromWxJieLongRequest;
import com.ddf.group.purchase.core.application.GroupPurchaseApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>团购业务层</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/29 23:17
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class GroupPurchaseApplicationServiceImpl implements GroupPurchaseApplicationService {

    @Override
    public void createFromWxJieLong(CreateFromWxJieLongRequest request) {

    }
}
