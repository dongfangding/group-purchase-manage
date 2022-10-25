package com.ddf.group.purchase.core.model.cqrs.group;

import com.ddf.boot.common.api.model.common.PageRequest;
import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>团购列表分页查询</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/08/26 14:06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupListQuery implements PageRequest, Serializable {

    private static final long serialVersionUID = 8107572972237949405L;

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

    /**
     * 发布标识
     */
    private Boolean publicFlag;

}
