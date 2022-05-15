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
    * 注册用户信息
    */
@TableName(value = "user_info")
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -954717757566835209L;

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
    private Integer buildingNo;

    /**
     * 房间号
     */
    @TableField(value = "room_no")
    private Integer roomNo;

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
     * 手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 登录密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 注册秒时间戳
     */
    @TableField(value = "ctime")
    private Integer ctime;
}
