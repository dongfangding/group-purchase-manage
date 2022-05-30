package com.ddf.group.purchase.api.enume;

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
    CREATED(1, "已创建"),
    ARRIVED(2, "已到货"),
    COMPLETED(3, "已完成"),
    CANCELED(4, "已取消"),
    ;

    @Getter
    private final Integer value;
    @Getter
    private final String desc;

    GroupPurchaseStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


}
