package com.ddf.group.purchase.core.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2022/07/17 15:33
*/


/**
 * 团购主表信息
 */
@TableName(value = "group_purchase_info")
public class GroupPurchaseInfo {
    private static final long serialVersionUID = 1L;
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

    /**
     * 是否公开到团购市场，不公开的话，不会在市场展示
     */
    @TableField(value = "public_flag")
    private Boolean publicFlag;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_GROUP_MASTER_UID = "group_master_uid";

    public static final String COL_STATUS = "status";

    public static final String COL_REMARK = "remark";

    public static final String COL_CTIME = "ctime";

    public static final String COL_MTIME = "mtime";

    public static final String COL_PUBLIC_FLAG = "public_flag";

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取团购名称
     *
     * @return name - 团购名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置团购名称
     *
     * @param name 团购名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取团长uid
     *
     * @return group_master_uid - 团长uid
     */
    public Long getGroupMasterUid() {
        return groupMasterUid;
    }

    /**
     * 设置团长uid
     *
     * @param groupMasterUid 团长uid
     */
    public void setGroupMasterUid(Long groupMasterUid) {
        this.groupMasterUid = groupMasterUid;
    }

    /**
     * 获取状态
     * 1. 已创建
     * 2. 已到货
     * 3. 已完成
     * 4. 已取消
     *
     * @return status - 状态
     * 1. 已创建
     * 2. 已到货
     * 3. 已完成
     * 4. 已取消
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     * 1. 已创建
     * 2. 已到货
     * 3. 已完成
     * 4. 已取消
     *
     * @param status 状态
     *               1. 已创建
     *               2. 已到货
     *               3. 已完成
     *               4. 已取消
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取创建时间秒时间戳
     *
     * @return ctime - 创建时间秒时间戳
     */
    public Long getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间秒时间戳
     *
     * @param ctime 创建时间秒时间戳
     */
    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取更新时间秒时间戳
     *
     * @return mtime - 更新时间秒时间戳
     */
    public Long getMtime() {
        return mtime;
    }

    /**
     * 设置更新时间秒时间戳
     *
     * @param mtime 更新时间秒时间戳
     */
    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }

    /**
     * 获取是否公开到团购市场，不公开的话，不会在市场展示
     *
     * @return public_flag - 是否公开到团购市场，不公开的话，不会在市场展示
     */
    public Boolean getPublicFlag() {
        return publicFlag;
    }

    /**
     * 设置是否公开到团购市场，不公开的话，不会在市场展示
     *
     * @param publicFlag 是否公开到团购市场，不公开的话，不会在市场展示
     */
    public void setPublicFlag(Boolean publicFlag) {
        this.publicFlag = publicFlag;
    }
}