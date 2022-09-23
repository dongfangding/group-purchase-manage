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
* @date 2022/09/23 10:41
*/


/**
 * 注册用户信息
 */
@Data
@TableName(value = "user_info")
public class UserInfo implements Serializable {
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
     * 临时邮箱， 当邮箱未验证时存储在这个字段，当验证通过，再复制给正式邮箱字段，这样使用时方便
     */
    @TableField(value = "temp_email")
    private String tempEmail;

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

    private static final long serialVersionUID = 1L;
}