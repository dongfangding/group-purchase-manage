package com.ddf.group.purchase.core.model.entity;

import lombok.Data;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2026/02/03 23:45
 */
@Data
public class UserAddress {
    private static final long serialVersionUID = 1L;
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 楼号，10
     */
    private String buildingNo;

    /**
     * 房号，如101
     */
    private String roomNo;

    /**
     * 是否默认收货地址
     */
    private Boolean defaultFlag;
}