package com.ddf.group.purchase.api.enume;

import com.ddf.group.purchase.api.response.group.GroupPurchaseTraceDTO;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * <p>团购状态</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/30 23:04
 */
public enum GroupPurchaseStatusEnum {
    /**
     *      * 1. 已创建
     *      * 2. 已到货
     *      * 3. 已完成
     *      * 4. 已取消
     */
    UNKNOWN(0, "未知", 0),
    CREATED(1, "已创建", 1),
    GROUPED(2, "已成团", 2),
    ARRIVED(3, "已到货", 3),
    COMPLETED(4, "已完成", 4),
    CANCELED(5, "已取消", 5),
    ;

    @Getter
    private final Integer value;
    @Getter
    private final String desc;
    /**
     * 跟踪步骤，由于状态后续可能会加，这个字段用来定义定义状态的相连步骤关联关系
     */
    @Getter
    private final Integer step;

    private static final Map<Integer, GroupPurchaseStatusEnum> MAPPINGS;

    static {
        MAPPINGS = Arrays.stream(GroupPurchaseStatusEnum.values()).collect(Collectors.toMap(GroupPurchaseStatusEnum::getValue,
                Function.identity()));
    }

    GroupPurchaseStatusEnum(Integer value, String desc, Integer step) {
        this.value = value;
        this.desc = desc;
        this.step = step;
    }

    /**
     * 根据value反解析枚举对象
     *
     * @param value
     * @return
     */
    public static GroupPurchaseStatusEnum resolve(Integer value) {
        return MAPPINGS.getOrDefault(value, GroupPurchaseStatusEnum.UNKNOWN);
    }

    public static List<GroupPurchaseTraceDTO> listNormalStep() {
        return Lists.newArrayList(GroupPurchaseTraceDTO.of(CREATED.getValue(), null),
                GroupPurchaseTraceDTO.of(GROUPED.getValue(), null),
                GroupPurchaseTraceDTO.of(ARRIVED.getValue(), null),
                GroupPurchaseTraceDTO.of(COMPLETED.getValue(), null));
    }
}
