package com.ddf.group.purchase.model.entity;

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
    * 团购主表信息
    */
@TableName(value = "group_purchase_info")
@Data
public class GroupPurchaseInfo implements Serializable {
    private static final long serialVersionUID = 698403416499624182L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 团购名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 团长uid
     */
    @TableField(value = "group_master_uid")
    private Long groupMasterUid;

    /**
     * 状态
1. 已创建
2. 已到货
3. 已完成
4. 已取消
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 创建时间秒时间戳
     */
    @TableField(value = "ctime")
    private Integer ctime;

    /**
     * 更新时间秒时间戳
     */
    @TableField(value = "mtime")
    private Integer mtime;
}
