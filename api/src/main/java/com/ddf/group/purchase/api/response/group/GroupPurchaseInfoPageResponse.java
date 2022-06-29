package com.ddf.group.purchase.api.response.group;

import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import java.io.Serializable;
import lombok.Data;

/**
* <p>团购列表响应类</p >
*
* @author Snowball
* @version 1.0
* @date 2022/05/23 23:11
*/
@Data
public class GroupPurchaseInfoPageResponse implements Serializable {
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
     * 团长用户名
     */
    private String groupMasterName;

    /**
     * 团长所在小区id
     */
    private Integer groupMasterCommunityId;

    /**
     * 团长所在楼栋号
     */
    private String groupMasterBuildingNo;

    /**
     * 团长所在房间号
     */
    private String groupMasterRoomNo;

    /**
     * 状态
     * 1. 已创建
     * 2. 已到货
     * 3. 已完成
     * 4. 已取消
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间秒时间戳
     */
    private Long ctime;

    /**
     * 更新时间秒时间戳
     */
    private Long mtime;

    public String getStatusName() {
        return GroupPurchaseStatusEnum.resolve(status).getDesc();
    }
}
