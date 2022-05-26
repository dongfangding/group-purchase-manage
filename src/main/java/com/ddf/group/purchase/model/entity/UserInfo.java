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
* @date 2022/05/23 23:11
*/


/**
 * 注册用户信息
 */
@Data
@TableName(value = "user_info")
public class UserInfo implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
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
    private Long ctime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_COMMUNITY_ID = "community_id";

    public static final String COL_BUILDING_NO = "building_no";

    public static final String COL_ROOM_NO = "room_no";

    public static final String COL_WX_ID = "wx_id";

    public static final String COL_WX_BUSINESS_CARD_QRCODE_URL = "wx_business_card_qrcode_url";

    public static final String COL_WX_COLLECTION_MONEY_QRCODE_URL = "wx_collection_money_qrcode_url";

    public static final String COL_EMAIL = "email";

    public static final String COL_MOBILE = "mobile";

    public static final String COL_PASSWORD = "password";

    public static final String COL_CTIME = "ctime";
}