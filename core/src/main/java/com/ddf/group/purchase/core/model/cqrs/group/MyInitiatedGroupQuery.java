package com.ddf.group.purchase.core.model.cqrs.group;

import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import lombok.Data;

/**
 * <p>我发起的团购查询</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/06/28 20:59
 */
@Data
public class MyInitiatedGroupQuery {

    /**
     * 团购名称，模糊搜索
     */
    private String groupName;

    /**
     * 团长uid
     */
    private Long groupMasterUid;

    /**
     * 团购状态
     */
    private GroupPurchaseStatusEnum status;

}
