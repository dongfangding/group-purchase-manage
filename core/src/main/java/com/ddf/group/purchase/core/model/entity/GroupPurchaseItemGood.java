package com.ddf.group.purchase.core.model.entity;

import java.math.BigDecimal;
import lombok.Data;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2026/02/04 19:16
*/
@Data
public class GroupPurchaseItemGood {
    private Long id;

    /**
    * 团购主表记录id
    */
    private Long groupPurchaseId;

    /**
    * 参团主表id
    */
    private Long groupPurchaseItemId;

    /**
    * 团购商品表id
    */
    private Long groupPurchaseGoodId;

    /**
    * 购买商品名称
    */
    private String groupPurchaseGoodName;

    /**
    * 购买商品图片
    */
    private String groupPurchaseGoodPic;

    /**
    * 参团用户id
    */
    private Long joinUid;

    /**
    * 购买商品数量
    */
    private Integer goodNum;

    /**
    * 购买时间
    */
    private Long ctime;

    /**
    * 修改时间
    */
    private Long mtime;

    /**
    * 商品单价
    */
    private BigDecimal price;

    /**
    * 购买商品描述
    */
    private String goodDescription;

    /**
    * 商品总价
    */
    private BigDecimal totalPrice;
}