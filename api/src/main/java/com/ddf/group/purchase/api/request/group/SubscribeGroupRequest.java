package com.ddf.group.purchase.api.request.group;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>订阅/取消订阅团购信息</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/07/17 20:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeGroupRequest implements Serializable  {
    private static final long serialVersionUID = 1516322558409231083L;

    /**
     * 团购id
     */
    @NotNull(message = "团购id不能为空")
    private Long groupId;

    /**
     * 是否订阅
     */
    private boolean subscribeFlag;
}
