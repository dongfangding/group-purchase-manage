package com.ddf.group.purchase.api.response.group;

import com.ddf.boot.common.core.util.DateUtils;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>参团明细</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/08/28 20:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupItemResponse implements Serializable {

    private static final long serialVersionUID = 7752475916214512464L;

    /**
     * 团购主表id
     */
    private Long groupPurchaseId;

    /**
     * 参团明细id
     */
    private Long groupItemId;

    /**
     * 参团时间，单位秒
     */
    private Long ctime;

    /**
     * 参团用户uid
     */
    private Long joinUid;

    /**
     * 参团用户名称
     */
    private String joinUserName;

    /**
     * 参团用户头像
     */
    private String joinUserAvatarUrl;

//    /**
//     * 购买商品明细
//     */
//    private List<Good> goods;

    // ==========暂时只支持一个商品============
    /**
     * 参团购买商品的记录id
     */
    private Long itemGoodId;

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品数量
     */
    private Integer goodNum;

    public String getFormatJoinTime() {
        return DateUtils.standardFormatSeconds(ctime);
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Good implements Serializable {

        private static final long serialVersionUID = 4411968484448559822L;

        /**
         * 参团购买商品的记录id
         */
        private Long id;

        /**
         * 参团主表记录id
         */
        private Long goodItemId;

        /**
         * 商品id
         */
        private Long goodId;

        /**
         * 商品名称
         */
        private String goodName;

        /**
         * 商品数量
         */
        private Integer goodNum;

    }
}