package com.ddf.group.purchase.core.mapper.ext;

import com.ddf.group.purchase.api.response.group.GroupPurchaseListResponse;
import com.ddf.group.purchase.core.mapper.GroupPurchaseInfoMapper;
import java.util.List;

/**
* <p>团购主表</p >
*
* @author Snowball
* @version 1.0
* @date 2022/05/15 20:49
*/
public interface GroupPurchaseInfoExtMapper extends GroupPurchaseInfoMapper {

    /**
     * 用户发布团购列表查询
     *
     * @return
     */
    List<GroupPurchaseListResponse> list();
}
