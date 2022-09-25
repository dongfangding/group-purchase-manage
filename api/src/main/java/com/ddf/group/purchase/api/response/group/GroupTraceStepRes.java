package com.ddf.group.purchase.api.response.group;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>团购状态跟踪数据，配合el-step的数据结构</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/25 22:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupTraceStepRes implements Serializable {

    private static final long serialVersionUID = 1516322558409231083L;

    /**
     * 当前激活步骤
     */
    private Integer currentStep;

    /**
     * 步骤列表
     */
    private List<GroupPurchaseTraceDTO> traces;
}
