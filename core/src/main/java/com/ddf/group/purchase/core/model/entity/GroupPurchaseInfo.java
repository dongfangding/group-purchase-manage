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
 * 团购主表信息
 */
@Data
public class GroupPurchaseInfo {
    private Long id;

    /**
    * 团购名称
    */
    private String name;

    /**
    * 团长uid
    */
    private Long groupMasterUid;

    /**
    * 状态
1. 已创建
2. 已到货
3. 已完成
4. 已取消
    */
    private Integer status;

    /**
    * 团购富文本内容
    */
    private String content;

    /**
    * 创建时间秒时间戳
    */
    private Long ctime;

    /**
    * 更新时间秒时间戳
    */
    private Long mtime;

    /**
    * 是否公开到团购市场，不公开的话，不会在市场展示
    */
    private Boolean publicFlag;

    /**
    * 个人微信名片二维码地址
    */
    private String wxIdCardUrl;

    /**
    * 图片附件地址，多个用逗号分隔
    */
    private String picUrls;

    /**
    * 视频附件地址
    */
    private String videoUrl;

    /**
    * 团购开始时间
    */
    private Long startTime;

    /**
    * 团购结束时间
    */
    private Long endTime;

    /**
    * 服务小区
    */
    private Long serviceCommunityId;
}