package com.ddf.group.purchase.api.response.group;

import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import java.io.Serializable;
import lombok.Data;

/**
* <p>团购状态跟踪</p >
*
* @author Snowball
* @version 1.0
* @date 2022/07/14 18:13
*/
@Data
public class GroupPurchaseTraceDTO implements Serializable {

    private Long id;

    /**
     * 团购记录id
     */
    private Long groupPurchaseId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 变更时间
     */
    private Long ctime;

    private String getStatusName () {
        return GroupPurchaseStatusEnum.resolve(status).getDesc();
    }

    private static final long serialVersionUID = -6290172047449593962L;
}
