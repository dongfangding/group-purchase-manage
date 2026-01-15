package com.ddf.group.purchase.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2022/08/20 20:25
*/


/**
 * 小区管理
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityBase {
    private static final long serialVersionUID = 8604619880193573465L;
    private Long id;

    /**
     * 小区名称
     */
    private String communityName;

    /**
     * 小区地址
     */
    private String communityAddress;

    /**
     * 是否无效
     */
    private Boolean invalidFlag;

    public static final String COL_ID = "id";

    public static final String COL_COMMUNITY_NAME = "community_name";

    public static final String COL_COMMUNITY_ADDRESS = "community_address";

    public static final String COL_INVALID_FLAG = "invalid_flag";
}
