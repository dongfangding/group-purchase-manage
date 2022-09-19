package com.ddf.group.purchase.api.request.user;

import java.io.Serializable;
import lombok.Data;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2022/09/06 19:44
*/
@Data
public class UserAddressRequest implements Serializable {
    private static final long serialVersionUID = 3549293556114272457L;

    private Long id;

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
