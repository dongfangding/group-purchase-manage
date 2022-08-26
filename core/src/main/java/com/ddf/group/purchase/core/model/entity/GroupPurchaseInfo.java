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
* @date 2022/08/20 21:13
*/


/**
 * 团购主表信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
     * 团购富文本内容
     */
    @TableField(value = "content")
    private String content;

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

    /**
     * 个人微信名片二维码地址
     */
    @TableField(value = "wx_id_card_url")
    private String wxIdCardUrl;

    /**
     * 图片附件地址，多个用逗号分隔
     */
    @TableField(value = "pic_urls")
    private String picUrls;

    /**
     * 视频附件地址
     */
    @TableField(value = "video_url")
    private String videoUrl;

    /**
     * 团购开始时间
     */
    @TableField(value = "start_time")
    private Long startTime;

    /**
     * 团购结束时间
     */
    @TableField(value = "end_time")
    private Long endTime;

    /**
     * 服务小区
     */
    @TableField(value = "service_community_id")
    private Long serviceCommunityId;
}
