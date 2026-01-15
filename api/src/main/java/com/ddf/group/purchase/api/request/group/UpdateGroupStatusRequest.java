package com.ddf.group.purchase.api.request.group;

import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>更改团购状态参数类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/07/14 18:30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGroupStatusRequest implements Serializable {
    private static final long serialVersionUID = 1516322558409231083L;

    /**blockPuzzle
     * id
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 」
     * 要更改的状态{@link GroupPurchaseStatusEnum}
     */
    @NotNull(message = "要更改的状态不能为空")
    private GroupPurchaseStatusEnum status;

    /**
     * 是否发送通知
     */
    private boolean notifyFlag;

}
