package com.ddf.group.purchase.api.enume;

import java.util.Arrays;
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
    UNKNOWN(0, "未知"),
    CREATED(1, "已创建"),
    GROUPED(2, "已成团"),
    ARRIVED(3, "已到货"),
    COMPLETED(4, "已完成"),
    CANCELED(5, "已取消"),
    ;

    @Getter
    private final Integer value;
    @Getter
    private final String desc;

    private static final Map<Integer, GroupPurchaseStatusEnum> MAPPINGS;

    static {
        MAPPINGS = Arrays.stream(GroupPurchaseStatusEnum.values()).collect(Collectors.toMap(GroupPurchaseStatusEnum::getValue,
                Function.identity()));
    }

    GroupPurchaseStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
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
}
