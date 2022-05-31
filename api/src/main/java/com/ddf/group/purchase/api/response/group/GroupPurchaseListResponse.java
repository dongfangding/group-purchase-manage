package com.ddf.group.purchase.api.response.group;

import java.io.Serializable;
import lombok.Data;

/**
* <p>团购主表列表响应</p >
*
* @author Snowball
* @version 1.0
* @date 2022/05/23 23:11
*/
@Data
public class GroupPurchaseListResponse implements Serializable {

    private static final long serialVersionUID = -7237625160468971690L;

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
     * 团长名称（手机号和楼栋号房间号等组合）
     */
    private String groupMasterName;

    /**
     * 状态
     * 1. 已创建
     * 2. 已到货
     * 3. 已完成
     * 4. 已取消
     */
    private Integer status;

    /**
     * 创建时间秒时间戳
     */
    private Long ctime;

    /**
     * 更新时间秒时间戳
     */
    private Long mtime;
}
