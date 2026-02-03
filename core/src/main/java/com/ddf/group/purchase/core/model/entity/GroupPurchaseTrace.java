package com.ddf.group.purchase.core.model.entity;

import lombok.Data;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2026/02/03 23:45
*/


/**
 * 团购状态跟踪
 */
@Data
public class GroupPurchaseTrace {
    private static final long serialVersionUID = 1L;
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
}