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
* @date 2022/07/14 18:13
*/
/**
    * 团购状态跟踪
    */
@Data
@TableName(value = "group_purchase_trace")
public class GroupPurchaseTrace implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 团购记录id
     */
    @TableField(value = "group_purchase_id")
    private Long groupPurchaseId;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 变更时间
     */
    @TableField(value = "ctime")
    private Long ctime;

    private static final long serialVersionUID = 1L;
}