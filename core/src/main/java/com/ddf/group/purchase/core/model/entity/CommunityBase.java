package com.ddf.group.purchase.core.model.entity;

import lombok.Data;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2026/02/03 23:45
*/


/**
 * 小区管理
 */
@Data
public class CommunityBase {
    public static final String COL_ID = "id";
    public static final String COL_COMMUNITY_NAME = "community_name";
    public static final String COL_COMMUNITY_ADDRESS = "community_address";
    public static final String COL_INVALID_FLAG = "invalid_flag";
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
}