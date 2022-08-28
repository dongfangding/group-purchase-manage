package com.ddf.group.purchase.core.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/08/28 15:51
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

    private static final long serialVersionUID = 1L;
}