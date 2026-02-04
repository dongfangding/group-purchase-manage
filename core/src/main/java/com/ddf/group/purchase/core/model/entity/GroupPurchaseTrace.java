package com.ddf.group.purchase.core.model.entity;

import lombok.Data;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2026/02/04 19:16
*/
/**
 * 团购状态跟踪
 */
@Data
public class GroupPurchaseTrace {
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