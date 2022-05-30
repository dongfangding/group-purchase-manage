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
* @date 2022/05/30 22:48
*/


/**
 * 注册用户信息
 */
@TableName(value = "user_info")
public class UserInfo {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 小区id
     */
    @TableField(value = "community_id")
    private Integer communityId;

    /**
     * 楼栋号
     */
    @TableField(value = "building_no")
    private String buildingNo;

    /**
     * 房间号
     */
    @TableField(value = "room_no")
    private String roomNo;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 微信名
     */
    @TableField(value = "wx_id")
    private String wxId;

    /**
     * 微信名片二维码图片地址
     */
    @TableField(value = "wx_business_card_qrcode_url")
    private String wxBusinessCardQrcodeUrl;

    /**
     * 微信收款二维码
     */
    @TableField(value = "wx_collection_money_qrcode_url")
    private String wxCollectionMoneyQrcodeUrl;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 邮箱是否已验证
     */
    @TableField(value = "email_verified")
    private Boolean emailVerified;

    /**
     * 头像地址url
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;

    /**
     * 头像地址缩略图
     */
    @TableField(value = "avatar_thumb_url")
    private String avatarThumbUrl;

    /**
     * 登录密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 注册秒时间戳
     */
    @TableField(value = "ctime")
    private Long ctime;

    public static final String COL_ID = "id";

    public static final String COL_COMMUNITY_ID = "community_id";

    public static final String COL_BUILDING_NO = "building_no";

    public static final String COL_ROOM_NO = "room_no";

    public static final String COL_NICKNAME = "nickname";

    public static final String COL_MOBILE = "mobile";

    public static final String COL_WX_ID = "wx_id";

    public static final String COL_WX_BUSINESS_CARD_QRCODE_URL = "wx_business_card_qrcode_url";

    public static final String COL_WX_COLLECTION_MONEY_QRCODE_URL = "wx_collection_money_qrcode_url";

    public static final String COL_EMAIL = "email";

    public static final String COL_EMAIL_VERIFIED = "email_verified";

    public static final String COL_AVATAR_URL = "avatar_url";

    public static final String COL_AVATAR_THUMB_URL = "avatar_thumb_url";

    public static final String COL_PASSWORD = "password";

    public static final String COL_CTIME = "ctime";

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
     * 设置小区id
     *
     * @param communityId 小区id
     */
    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    /**
     * 获取小区id
     *
     * @return community_id - 小区id
     */
    public Integer getCommunityId() {
        return communityId;
    }

    /**
     * 获取楼栋号
     *
     * @return building_no - 楼栋号
     */
    public String getBuildingNo() {
        return buildingNo;
    }

    /**
     * 设置楼栋号
     *
     * @param buildingNo 楼栋号
     */
    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    /**
     * 获取房间号
     *
     * @return room_no - 房间号
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * 设置房间号
     *
     * @param roomNo 房间号
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取微信名
     *
     * @return wx_id - 微信名
     */
    public String getWxId() {
        return wxId;
    }

    /**
     * 设置微信名
     *
     * @param wxId 微信名
     */
    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    /**
     * 获取微信名片二维码图片地址
     *
     * @return wx_business_card_qrcode_url - 微信名片二维码图片地址
     */
    public String getWxBusinessCardQrcodeUrl() {
        return wxBusinessCardQrcodeUrl;
    }

    /**
     * 设置微信名片二维码图片地址
     *
     * @param wxBusinessCardQrcodeUrl 微信名片二维码图片地址
     */
    public void setWxBusinessCardQrcodeUrl(String wxBusinessCardQrcodeUrl) {
        this.wxBusinessCardQrcodeUrl = wxBusinessCardQrcodeUrl;
    }

    /**
     * 获取微信收款二维码
     *
     * @return wx_collection_money_qrcode_url - 微信收款二维码
     */
    public String getWxCollectionMoneyQrcodeUrl() {
        return wxCollectionMoneyQrcodeUrl;
    }

    /**
     * 设置微信收款二维码
     *
     * @param wxCollectionMoneyQrcodeUrl 微信收款二维码
     */
    public void setWxCollectionMoneyQrcodeUrl(String wxCollectionMoneyQrcodeUrl) {
        this.wxCollectionMoneyQrcodeUrl = wxCollectionMoneyQrcodeUrl;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取邮箱是否已验证
     *
     * @return email_verified - 邮箱是否已验证
     */
    public Boolean getEmailVerified() {
        return emailVerified;
    }

    /**
     * 设置邮箱是否已验证
     *
     * @param emailVerified 邮箱是否已验证
     */
    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    /**
     * 获取头像地址url
     *
     * @return avatar_url - 头像地址url
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 设置头像地址url
     *
     * @param avatarUrl 头像地址url
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 获取头像地址缩略图
     *
     * @return avatar_thumb_url - 头像地址缩略图
     */
    public String getAvatarThumbUrl() {
        return avatarThumbUrl;
    }

    /**
     * 设置头像地址缩略图
     *
     * @param avatarThumbUrl 头像地址缩略图
     */
    public void setAvatarThumbUrl(String avatarThumbUrl) {
        this.avatarThumbUrl = avatarThumbUrl;
    }

    /**
     * 获取登录密码
     *
     * @return password - 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     *
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取注册秒时间戳
     *
     * @return ctime - 注册秒时间戳
     */
    public Long getCtime() {
        return ctime;
    }

    /**
     * 设置注册秒时间戳
     *
     * @param ctime 注册秒时间戳
     */
    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }
}
