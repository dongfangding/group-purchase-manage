package com.ddf.group.purchase.api.request.group;

import com.ddf.boot.common.core.model.PageRequest;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>我的参团列表查询</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/06/28 20:59
 */
@Data
public class MyJoinGroupPageRequest implements Serializable, PageRequest {
    private static final long serialVersionUID = 1516322558409231083L;

    @NotNull(message = "pageNum不能为空")
    private Integer pageNum;

    @NotNull(message = "pageSize不能为空")
    private Integer pageSize;

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
    private Integer status;

}