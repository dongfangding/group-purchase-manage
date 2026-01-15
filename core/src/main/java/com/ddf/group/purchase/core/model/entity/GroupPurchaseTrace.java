package com.ddf.group.purchase.core.model.entity;

import java.io.Serializable;
import lombok.Data;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2022/07/14 18:13
*/
/**
    * 团购状态跟踪
    */
@Data
public class GroupPurchaseTrace implements Serializable {
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

    private static final long serialVersionUID = 1L;
}
