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
* @date 2022/08/29 16:37
*/
/**
    * 字典表
    */
@Data
@TableName(value = "sys_dict")
public class SysDict implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典类型
     */
    @TableField(value = "dict_type_code")
    private String dictTypeCode;

    /**
     * 字典名称
     */
    @TableField(value = "dict_type_name")
    private String dictTypeName;

    /**
     * 字典明细代码
     */
    @TableField(value = "dict_detail_code")
    private String dictDetailCode;

    /**
     * 字段明细名称
     */
    @TableField(value = "dict_detail_name")
    private String dictDetailName;

    /**
     * 请求参数，比如这个字段后台映射为枚举的时候，那么字典是用来渲染的，但是请求的时候却是要用对应属性的枚举名，就是这个字段
     */
    @TableField(value = "request_value")
    private String requestValue;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 是否有效
     */
    @TableField(value = "active")
    private Boolean active;

    private static final long serialVersionUID = 1L;
}