package com.ddf.group.purchase.core.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/05 19:17
 */
@Data
@TableName(value = "group_purchase_item_good")
public class GroupPurchaseItemGood implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 团购主表记录id
     */
    @TableField(value = "group_purchase_id")
    private Long groupPurchaseId;

    /**
     * 参团主表id
     */
    @TableField(value = "group_purchase_item_id")
    private Long groupPurchaseItemId;

    /**
     * 团购商品表id
     */
    @TableField(value = "group_purchase_good_id")
    private Long groupPurchaseGoodId;

    /**
     * 购买商品名称
     */
    @TableField(value = "group_purchase_good_name")
    private String groupPurchaseGoodName;

    /**
     * 购买商品图片
     */
    @TableField(value = "group_purchase_good_pic")
    private String groupPurchaseGoodPic;

    /**
     * 参团用户id
     */
    @TableField(value = "join_uid")
    private Long joinUid;

    /**
     * 购买商品数量
     */
    @TableField(value = "good_num")
    private Integer goodNum;

    /**
     * 购买时间
     */
    @TableField(value = "ctime")
    private Long ctime;

    /**
     * 修改时间
     */
    @TableField(value = "mtime")
    private Long mtime;

    /**
     * 商品单价
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 购买商品描述
     */
    @TableField(value = "good_description")
    private String goodDescription;

    /**
     * 商品总价
     */
    @TableField(value = "total_price")
    private BigDecimal totalPrice;

    private static final long serialVersionUID = 1L;
}