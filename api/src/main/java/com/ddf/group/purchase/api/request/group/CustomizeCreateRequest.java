package com.ddf.group.purchase.api.request.group;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>自定义创建团购内容</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/07/12 16:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomizeCreateRequest implements Serializable {

    private static final long serialVersionUID = 1516322558409231083L;

    /**
     * 团购名称
     */
    @NotBlank(message = "团购名称不能为空")
    @Size(max = 128, message = "团购名称长度不能超过128")
    private String name;

    /**
     * 备注
     */
    @Size(max = 3000, message = "团购内容长度不能超过3000")
    private String remark;
}
