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
 * 注册用户信息
 */
@Data
public class UserInfo {
    private Long id;

    /**
    * 小区id
    */
    private Integer communityId;

    /**
    * 楼栋号
    */
    private String buildingNo;

    /**
    * 房间号
    */
    private String roomNo;

    /**
    * 昵称
    */
    private String nickname;

    /**
    * 手机号
    */
    private String mobile;

    /**
    * 微信名
    */
    private String wxId;

    /**
    * 微信名片二维码图片地址
    */
    private String wxBusinessCardQrcodeUrl;

    /**
    * 微信收款二维码
    */
    private String wxCollectionMoneyQrcodeUrl;

    /**
    * 临时邮箱， 当邮箱未验证时存储在这个字段，当验证通过，再复制给正式邮箱字段，这样使用时方便
    */
    private String tempEmail;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 邮箱是否已验证
    */
    private Boolean emailVerified;

    /**
    * 头像地址url
    */
    private String avatarUrl;

    /**
    * 头像地址缩略图
    */
    private String avatarThumbUrl;

    /**
    * 登录密码
    */
    private String password;

    /**
    * 注册秒时间戳
    */
    private Long ctime;
}