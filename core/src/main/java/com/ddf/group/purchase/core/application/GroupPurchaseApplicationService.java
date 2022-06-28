package com.ddf.group.purchase.core.application;

import com.ddf.group.purchase.api.request.group.CreateFromWxJieLongRequest;
import com.ddf.group.purchase.api.request.group.GroupPurchaseInfoPageRequest;
import com.ddf.group.purchase.api.response.group.GroupPurchaseInfoPageResponse;
import com.github.pagehelper.PageInfo;

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

    /**
     * 团购列表查询
     *
     * @param request
     * @return
     */
    PageInfo<GroupPurchaseInfoPageResponse> pageList(GroupPurchaseInfoPageRequest request);
}
