package com.ddf.group.purchase.api.request.group;

import com.ddf.boot.common.api.model.common.PageRequest;
import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * <p>我发起的团购查询</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/06/28 20:59
 */
@Data
public class MyInitiatedGroupPageRequest implements Serializable, PageRequest {
    private static final long serialVersionUID = 1516322558409231083L;

    @NotNull(message = "pageNum不能为空")
    private Integer pageNum;

    @NotNull(message = "pageSize不能为空")
    private Integer pageSize;

    /**
     * 团购名称，模糊搜索
     */
    @Size(max = 128, message = "团购名称长度不能超过128")
    private String groupName;

    /**
     * 团购状态
     */
    private GroupPurchaseStatusEnum status;

}
