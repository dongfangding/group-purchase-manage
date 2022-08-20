package com.ddf.group.purchase.core.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "community_base")
public class CommunityBase {
    private static final long serialVersionUID = 8604619880193573465L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 小区名称
     */
    @TableField(value = "community_name")
    private String communityName;

    /**
     * 小区地址
     */
    @TableField(value = "community_address")
    private String communityAddress;

    /**
     * 是否无效
     */
    @TableField(value = "invalid_flag")
    private Boolean invalidFlag;

    public static final String COL_ID = "id";

    public static final String COL_COMMUNITY_NAME = "community_name";

    public static final String COL_COMMUNITY_ADDRESS = "community_address";

    public static final String COL_INVALID_FLAG = "invalid_flag";
}