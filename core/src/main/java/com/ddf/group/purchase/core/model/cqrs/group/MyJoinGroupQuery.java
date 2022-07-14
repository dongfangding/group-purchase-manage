package com.ddf.group.purchase.core.model.cqrs.group;

import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import lombok.Data;

/**
 * <p>我的参团列表查询</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/06/28 20:59
 */
@Data
public class MyJoinGroupQuery {
    private static final long serialVersionUID = 1516322558409231083L;


    /**
     * 团购名称，模糊搜索
     */
    private String groupName;

    /**
     * 参团用户uid
     */
    private Long joinUid;

    /**
     * 团购状态
     */
    private GroupPurchaseStatusEnum status;

    /**
     * 团长uid
     */
    private Long groupMasterUid;

}
