package com.ddf.group.purchase.core.model.entity;

import java.io.Serializable;
import lombok.Data;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2022/11/25 11:44
*/


/**
 * 用户参团记录
 */
@Data
public class GroupPurchaseItem implements Serializable {
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 团购主表记录id
     */
    private Long groupPurchaseId;

    /**
     * 参团用户id
     */
    private Long joinUid;

    /**
     * 创建时间
     */
    private Long ctime;

    /**
     * 是否订阅最新信息（状态和备注更新变化）
     */
    private Boolean subscribeProgress;

    /**
     * 支付状态 0 待支付 1 已支付 2 已退款  3 已完成
     */
    private Integer joinStatus;

    /**
     * 状态变更时间
     */
    private Long statusChangeTime;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人手机号
     */
    private String receiverMobile;

    /**
     * 收货人详细地址
     */
    private String receiverDetailAddress;

    /**
     * 收货人省份
     */
    private String receiverProvince;

    /**
     * 收货人市
     */
    private String receiverCity;

    /**
     * 收货人区
     */
    private String receiverArea;

    /**
     * 收货人楼栋号
     */
    private String receiverBuildingNo;

    /**
     * 收货人房间号
     */
    private String receiverRoomNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否已支付
     */
    private Boolean paidFlag;

    /**
     * 支付倒计时秒
     */
    private Integer payCountDownSeconds;

    /**
     * 支付时间戳
     */
    private Long payTime;

    private static final long serialVersionUID = 1L;
}
