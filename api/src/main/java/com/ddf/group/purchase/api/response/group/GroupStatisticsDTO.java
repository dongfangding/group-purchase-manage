package com.ddf.group.purchase.api.response.group;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>团购统计相关DTO</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/16 18:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupStatisticsDTO implements Serializable {

    private static final long serialVersionUID = 6110802794069500443L;

    /**
     * 查看次数
     */
    private Long viewCount;

    /**
     * 支付次数
     */
    private Long payCount;

    /**
     * 退货次数
     */
    private Long returnCount;

    public static GroupStatisticsDTO init() {
        return GroupStatisticsDTO.builder().viewCount(0L)
                .payCount(0L)
                .returnCount(0L)
                .build();
    }
}
