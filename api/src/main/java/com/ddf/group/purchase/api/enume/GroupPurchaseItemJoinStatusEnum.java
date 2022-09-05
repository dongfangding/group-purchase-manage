package com.ddf.group.purchase.api.enume;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * <p>参团明细订单状态</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/30 23:04
 */
public enum GroupPurchaseItemJoinStatusEnum {
    /**
     *
     */
    UNKNOWN(-1, "未定义"),
    WAIT_PAY(0, "待支付"),
    PAID(1, "已支付"),
    COMPLETE(2, "已完成"),
    REFUND(3, "已退款")
    ;

    @Getter
    private final Integer value;
    @Getter
    private final String desc;

    private static final Map<Integer, GroupPurchaseItemJoinStatusEnum> MAPPINGS;

    static {
        MAPPINGS = Arrays.stream(GroupPurchaseItemJoinStatusEnum.values()).collect(Collectors.toMap(
                GroupPurchaseItemJoinStatusEnum::getValue,
                Function.identity()));
    }

    GroupPurchaseItemJoinStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据value反解析枚举对象
     *
     * @param value
     * @return
     */
    public static GroupPurchaseItemJoinStatusEnum resolve(Integer value) {
        return MAPPINGS.getOrDefault(value, GroupPurchaseItemJoinStatusEnum.UNKNOWN);
    }
}
