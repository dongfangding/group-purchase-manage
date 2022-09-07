package com.ddf.group.purchase.api.request.group;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * <p>参团请求</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/08/28 15:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinGroupRequest implements Serializable {

    private static final long serialVersionUID = 3815670259833915945L;

    /**
     * 团购id
     */
    @NotNull(message = "团购id不能为空")
    private Long groupId;

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
