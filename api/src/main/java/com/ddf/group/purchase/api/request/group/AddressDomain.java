package com.ddf.group.purchase.api.request.group;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>地址对象</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/07 20:19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDomain implements Serializable {

    private static final long serialVersionUID = 1336487965503922368L;

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
}
