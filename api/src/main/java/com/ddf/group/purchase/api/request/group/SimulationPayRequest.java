package com.ddf.group.purchase.api.request.group;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

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
     * 备注
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
}
