package com.ddf.group.purchase.core.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2022/05/15 20:49
*/


/**
    * 小区管理
    */
@TableName(value = "community_base")
@Data
public class CommunityBase implements Serializable {

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

}
