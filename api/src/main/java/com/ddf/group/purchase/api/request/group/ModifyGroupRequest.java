package com.ddf.group.purchase.api.request.group;

import java.io.Serializable;
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
     * 团购名称，模糊搜索
     */
    @Size(max = 128, message = "团购名称长度不能超过128")
    private String groupName;

    @Size(max = 3000, message = "团购内容长度不能超过3000")
    private String remark;

}
