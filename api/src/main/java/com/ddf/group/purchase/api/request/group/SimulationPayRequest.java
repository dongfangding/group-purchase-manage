package com.ddf.group.purchase.api.request.group;

import java.io.Serializable;
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
}
