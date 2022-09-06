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
 * @date 2022/09/06 19:46
 */
@Data
@TableName(value = "user_address")
public class UserAddress implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "`uid`")
    private Long uid;

    /**
     * 省
     */
    @TableField(value = "province")
    private String province;

    /**
     * 市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 区
     */
    @TableField(value = "area")
    private String area;

    /**
     * 详细地址
     */
    @TableField(value = "detail_address")
    private String detailAddress;

    /**
     * 联系方式
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 收货人姓名
     */
    @TableField(value = "receiver_name")
    private String receiverName;

    /**
     * 楼号，10
     */
    @TableField(value = "building_no")
    private String buildingNo;

    /**
     * 房号，如101
     */
    @TableField(value = "room_no")
    private String roomNo;

    private static final long serialVersionUID = 1L;
}