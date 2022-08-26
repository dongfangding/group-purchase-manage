package com.ddf.group.purchase.core.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2022/08/20 23:01
*/


/**
 * 团购商品表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "group_purchase_good")
public class GroupPurchaseGood {
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
}
