package com.ddf.group.purchase.api.request.group;

import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>发布/取消发布团购</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/07/17 17:06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublishGroupRequest implements Serializable {

    private static final long serialVersionUID = 1516322558409231083L;

    /**
     * 团购id
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * true 发布到市场
     * false 取消发布
     */
    private boolean publicFlag;

}
