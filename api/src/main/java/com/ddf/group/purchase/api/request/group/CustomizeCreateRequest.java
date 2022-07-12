package com.ddf.group.purchase.api.request.group;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
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
    private String name;

    /**
     * 备注
     */
    private String remark;
}
