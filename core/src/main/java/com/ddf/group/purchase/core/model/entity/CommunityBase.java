package com.ddf.group.purchase.core.model.entity;

import lombok.Data;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2026/02/04 19:16
*/
/**
 * 小区管理
 */
@Data
public class CommunityBase {
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