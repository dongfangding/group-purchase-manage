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
* @date 2022/09/21 15:41
*/


/**
 * 用户参团记录
 */
@Data
@TableName(value = "group_purchase_item")
public class GroupPurchaseItem implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号
     */
    @TableField(value = "order_no")
    private String orderNo;

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
    private Long ctime;

    /**
     * 是否订阅最新信息（状态和备注更新变化）
     */
    @TableField(value = "subscribe_progress")
    private Boolean subscribeProgress;

    /**
     * 支付状态 0 待支付 1 已支付 2 已退款  3 已完成
     */
    @TableField(value = "join_status")
    private Integer joinStatus;

    /**
     * 状态变更时间
     */
    @TableField(value = "status_change_time")
    private Long statusChangeTime;

    /**
     * 收货人姓名
     */
    @TableField(value = "receiver_name")
    private String receiverName;

    /**
     * 收货人手机号
     */
    @TableField(value = "receiver_mobile")
    private String receiverMobile;

    /**
     * 收货人详细地址
     */
    @TableField(value = "receiver_detail_address")
    private String receiverDetailAddress;

    /**
     * 收货人省份
     */
    @TableField(value = "receiver_province")
    private String receiverProvince;

    /**
     * 收货人市
     */
    @TableField(value = "receiver_city")
    private String receiverCity;

    /**
     * 收货人区
     */
    @TableField(value = "receiver_area")
    private String receiverArea;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 是否已支付
     */
    @TableField(value = "paid_flag")
    private Boolean paidFlag;

    /**
     * 支付倒计时秒
     */
    @TableField(value = "pay_count_down_seconds")
    private Integer payCountDownSeconds;

    /**
     * 支付时间戳
     */
    @TableField(value = "pay_time")
    private Long payTime;

    private static final long serialVersionUID = 1L;
}