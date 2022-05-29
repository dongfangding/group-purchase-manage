package com.ddf.group.purchase.core.application;

import com.ddf.group.purchase.api.request.group.CreateFromWxJieLongRequest;

/**
 * <p>团购业务层</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/29 23:16
 */
public interface GroupPurchaseApplicationService {

    /**
     * 基于微信接龙文案创建团购信息
     *
     * @param request
     */
    void createFromWxJieLong(CreateFromWxJieLongRequest request);
}
