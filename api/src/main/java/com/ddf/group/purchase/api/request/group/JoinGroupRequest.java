package com.ddf.group.purchase.api.request.group;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Min(value = 1, message = "商品数量不合法")
    @Max(value = 99999, message = "商品数量不合法")
    private Integer goodNum;
}
