package com.ddf.group.purchase.api.request.group;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/07/12 20:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifyGroupRequest implements Serializable {
    private static final long serialVersionUID = 1516322558409231083L;

    @NotNull(message = "团购id不能为空")
    private Long id;

    /**
     * 团购名称
     */
    @NotBlank(message = "团购名称不能为空")
    @Size(max = 128, message = "团购名称长度不能超过128")
    private String name;

    /**
     * 团购富文本内容
     */
    @Size(max = 3000, message = "团购富文本内容长度不能超过3000")
    private String content;

    /**
     * 是否公开到团购市场，不公开的话，不会在市场展示
     */
    private Boolean publicFlag;

    /**
     * 个人微信名片二维码地址
     */
    @NotBlank(message = "团购名称不能为空")
    @Size(max = 128, message = "团购名称长度不能超过128")
    private String wxIdCardUrl;

    /**
     * 图片附件地址，多个用逗号分隔
     */
    @Size(max = 512, message = "图片附件地址长度不能超过512")
    private String picUrls;

    /**
     * 视频附件地址
     */
    @Size(max = 128, message = "视频附件地址长度不能超过128")
    private String videoUrl;

    /**
     * 团购开始时间
     */
    @NotNull(message = "团购开始时间不能为空")
    private Long startTime;

    /**
     * 团购结束时间
     */
    @NotNull(message = "团购结束时间不能为空")
    private Long endTime;

    // 商品信息
    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String goodName;

    /**
     * 商品描述
     */
    private String goodDescription;

    /**
     * 商品单价
     */
    @NotNull(message = "商品单价不能为空")
    private BigDecimal price;

    /**
     * 商品库存
     */
    @NotNull(message = "商品库存不能为空")
    private Integer stock;

    /**
     * 限制类型，默认不限购，如限购数量上限、起购数量
     */
    private String limitType;

    private Long limitValue;

}
