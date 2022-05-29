package com.ddf.group.purchase.api.response.user;

import java.io.Serializable;
import lombok.Data;

/**
 * <p>个人中心返回对象</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/29 22:22
 */
@Data
public class PersonalInfoResponse implements Serializable {

    private static final long serialVersionUID = 1516322558409231083L;

    private Long id;

    /**
     * 小区id
     */
    private Integer communityId;

    /**
     * 小区名称
     */
    private String communityName;

    /**
     * 小区地址
     */
    private String communityAddress;

    /**
     * 楼栋号
     */
    private Integer buildingNo;

    /**
     * 房间号
     */
    private Integer roomNo;

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
     * 注册秒时间戳
     */
    private Long ctime;
}
