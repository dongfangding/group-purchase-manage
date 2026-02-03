package com.ddf.group.purchase.core.model.entity;

import java.math.BigDecimal;
import lombok.Data;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2026/02/03 23:45
*/


/**
 * 团购商品表
 */
@Data
public class GroupPurchaseGood {
    private static final long serialVersionUID = 1L;
    private Long id;

    /**
     * 团购主表id
     */
    private Long groupPurchaseId;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品描述
     */
    private String goodDescription;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 商品库存
     */
    private Integer stock;

    /**
     * 限制类型，默认不限购，如限购数量上限、起购数量
     */
    private String limitType;

    /**
     * 限制数量，与limit_type匹配使用
     */
    private Long limitValue;

    /**
     * 商品图片
     */
    private String goodPic;

    /**
     * 规格json数组， 第一层数组是规格名称，如尺码、口味； 第二层是具体规格的数值， 如尺码里有S/M/L
     */
    private String specification;

    /**
     * 剩余商品库存
     */
    private Integer remainingStock;
}