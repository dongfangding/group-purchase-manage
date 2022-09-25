package com.ddf.group.purchase.api.response.group;

import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* <p>团购状态跟踪</p >
*
* @author Snowball
* @version 1.0
* @date 2022/07/14 18:13
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class GroupPurchaseTraceDTO implements Serializable {

    /**
     * 状态
     */
    private Integer status;

    /**
     * 变更时间
     */
    private Long ctime;

    public String getStatusName () {
        return GroupPurchaseStatusEnum.resolve(status).getDesc();
    }

    private static final long serialVersionUID = -6290172047449593962L;
}
