package com.ddf.group.purchase.api.response.group;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>订单商品</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/21 15:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupPurchaseItemGoodResponse implements Serializable {

    private static final long serialVersionUID = 4411968484448559822L;

    /**
     */
    private Long id;

    /**
     * 团购主表id
     */
    private Long groupPurchaseId;

    /**
     * 团购订单id
     */
    private Long groupPurchaseItemId;

    /**
     * 团购主表商品id
     */
    private Long groupPurchaseGoodId;

    /**
     * 商品名称
     */
    private String groupPurchaseGoodName;

    /**
     * 商品图片
     */
    private String groupPurchaseGoodPic;

    /**
     * 商品图片
     */
    private Long joinUid;

    /**
     * 商品数量
     */
    private Integer goodNum;

    /**
     * 商品描述
     */
    private String goodDescription;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 商品总价
     */
    private BigDecimal totalPrice;

}
