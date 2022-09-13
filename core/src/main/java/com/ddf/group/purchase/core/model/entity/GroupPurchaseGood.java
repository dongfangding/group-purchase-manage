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
* @date 2022/09/13 18:45
*/


/**
 * 团购商品表
 */
@Data
@TableName(value = "group_purchase_good")
public class GroupPurchaseGood implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 团购主表id
     */
    @TableField(value = "group_purchase_id")
    private Long groupPurchaseId;

    /**
     * 商品名称
     */
    @TableField(value = "good_name")
    private String goodName;

    /**
     * 商品描述
     */
    @TableField(value = "good_description")
    private String goodDescription;

    /**
     * 商品单价
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 商品库存
     */
    @TableField(value = "stock")
    private Integer stock;

    /**
     * 限制类型，默认不限购，如限购数量上限、起购数量
     */
    @TableField(value = "limit_type")
    private String limitType;

    /**
     * 限制数量，与limit_type匹配使用
     */
    @TableField(value = "limit_value")
    private Long limitValue;

    /**
     * 商品图片
     */
    @TableField(value = "good_pic")
    private String goodPic;

    /**
     * 规格json数组， 第一层数组是规格名称，如尺码、口味； 第二层是具体规格的数值， 如尺码里有S/M/L
     */
    @TableField(value = "specification")
    private String specification;

    /**
     * 剩余商品库存
     */
    @TableField(value = "remaining_stock")
    private Integer remainingStock;

    private static final long serialVersionUID = 1L;
}