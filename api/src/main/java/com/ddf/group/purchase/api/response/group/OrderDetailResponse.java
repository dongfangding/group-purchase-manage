package com.ddf.group.purchase.api.response.group;

import com.ddf.group.purchase.api.enume.GroupPurchaseItemJoinStatusEnum;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>订单明细</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/21 15:29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse implements Serializable {

    private static final long serialVersionUID = -3850866589861399237L;

    /**
     * 订单id
     */
    private Long joinItemId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 团购主表记录id
     */
    private Long groupPurchaseId;

    /**
     * 团购名称
     */
    private String groupName;

    /**
     * 团长uid
     */
    private Long groupMasterUid;

    /**
     * 团长昵称
     */
    private String groupMasterNickname;

    /**
     * 团长头像
     */
    private String groupMasterAvatarUrl;

    /**
     * 参团用户id
     */
    private Long joinUid;

    /**
     * 参团人昵称
     */
    private String joinNickname;

    /**
     * 参团人头像
     */
    private String joinAvatarUrl;

    /**
     * 创建时间
     */
    private Long joinTime;

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
     * 支付时间
     */
    private Long payTime;

    /**
     * 订单商品信息
     */
    private GroupPurchaseItemGoodResponse orderGood;

    /**
     * 团购状态跟踪数据
     */
    private GroupTraceStepRes trace;

    public String getJoinStatusName() {
        return GroupPurchaseItemJoinStatusEnum.resolve(joinStatus).getDesc();
    }
}
