package com.ddf.group.purchase.api.response.group;

import com.ddf.group.purchase.api.enume.GroupPurchaseItemJoinStatusEnum;
import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
* <p>团购列表响应类</p >
*
* @author Snowball
* @version 1.0
* @date 2022/05/23 23:11
*/
@Data
public class MyJoinGroupPageResponse implements Serializable {

    private Long groupId;

    /**
     * 团购名称
     */
    private String name;

    /**
     * 团长uid
     */
    private Long groupMasterUid;

    /**
     * 团长用户名
     */
    private String groupMasterName;

    /**
     * 团长所在小区id
     */
    private Integer groupMasterCommunityId;

    /**
     * 团长所在楼栋号
     */
    private String groupMasterBuildingNo;

    /**
     * 团长所在房间号
     */
    private String groupMasterRoomNo;

    /**
     * 团购名称
     */
    private String groupPurchaseName;

    /**
     * 状态
     * 1. 已创建
     * 2. 已到货
     * 3. 已完成
     * 4. 已取消
     */
    private Integer status;

    /**
     * 备注
     */
    private String content;

    /**
     * 创建时间秒时间戳
     */
    private Long ctime;

    /**
     * 更新时间秒时间戳
     */
    private Long mtime;

    /**
     * 参团明细记录id
     */
    private Long joinItemId;

    /**
     * 参团时间
     */
    private Long joinTime;

    /**
     * 是否订阅团购最新信息
     */
    private Boolean subscribeProgress;

    /**
     * 参团状态
     */
    private Integer joinStatus;

    /**
     * 订单状态变更时间
     */
    private Long statusChangeTime;

    /**
     * 参团备注
     */
    private String remark;

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

    // -------------商品信息

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品描述
     */
    private String goodDescription;

    /**
     * 商品图片
     */
    private String goodPic;

    private Integer goodNum;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 商品单价
     */
    private BigDecimal totalPrice;

    public String getStatusName() {
        return GroupPurchaseStatusEnum.resolve(status).getDesc();
    }
    public String getJoinStatusName() {
        return GroupPurchaseItemJoinStatusEnum.resolve(joinStatus).getDesc();
    }
}
