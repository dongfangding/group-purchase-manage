package com.ddf.group.purchase.api.request.group;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * <p>假支付请求参数</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/06 16:11
 */
@Data
public class SimulationPayRequest implements Serializable {

    private static final long serialVersionUID = 1516322558409231083L;

    /**
     * 参团记录id
     */
    @NotNull(message = "参团记录id不能为空")
    private Long joinItemId;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 商品记录id
     */
    @NotNull(message = "商品记录id不能为空")
    private Long goodId;

    /**
     * 商品数量
     */
    @NotNull(message = "商品数量不能为空")
    @Range(min = 1, max = 99999, message = "商品数量不合法")
    private Integer goodNum;

    /**
     * 参团备注
     */
    private String remark;

    /**
     * 收货人姓名
     */
    @NotBlank(message = "收货人不能为空")
    private String receiverName;

    /**
     * 收货人手机号
     */
    @NotBlank(message = "收货人联系方式为空")
    private String receiverMobile;

    /**
     * 收货人详细地址
     */
    @NotBlank(message = "收货人详细地址为空")
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
