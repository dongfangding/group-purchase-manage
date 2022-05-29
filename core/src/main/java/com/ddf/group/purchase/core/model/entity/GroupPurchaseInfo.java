package com.ddf.group.purchase.core.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
* <p>团购主表信息</p >
*
* @author Snowball
* @version 1.0
* @date 2022/05/23 23:11
*/
@Data
@TableName(value = "group_purchase_info")
public class GroupPurchaseInfo implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
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
     * 1. 已创建
     * 2. 已到货
     * 3. 已完成
     * 4. 已取消
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
    private Long ctime;

    /**
     * 更新时间秒时间戳
     */
    @TableField(value = "mtime")
    private Long mtime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_GROUP_MASTER_UID = "group_master_uid";

    public static final String COL_STATUS = "status";

    public static final String COL_REMARK = "remark";

    public static final String COL_CTIME = "ctime";

    public static final String COL_MTIME = "mtime";
}
