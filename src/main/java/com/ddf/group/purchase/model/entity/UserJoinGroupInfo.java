package com.ddf.group.purchase.model.entity;

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
* @date 2022/05/15 20:49
*/
/**
    * 用户参团记录
    */
@TableName(value = "user_join_group_info")
@Data
public class UserJoinGroupInfo implements Serializable {

    private static final long serialVersionUID = -999556376522282441L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 团购主表记录id
     */
    @TableField(value = "group_purchase_id")
    private Long groupPurchaseId;

    /**
     * 参团用户id
     */
    @TableField(value = "join_uid")
    private Long joinUid;

    /**
     * 创建时间
     */
    @TableField(value = "ctime")
    private Integer ctime;

    /**
     * 是否订阅最新信息（状态和备注更新变化）
     */
    @TableField(value = "subscribe_progress")
    private Boolean subscribeProgress;
}