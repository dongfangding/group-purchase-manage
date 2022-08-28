package com.ddf.group.purchase.core.mapper.ext;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddf.group.purchase.api.response.group.GroupItemResponse;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2022/08/28 15:04
*/
public interface GroupPurchaseItemExtMapper extends BaseMapper<GroupPurchaseItem> {

    /**
     * 查询参团明细（商品和明细一对一）
     *
     * @param groupId
     * @return
     */
    List<GroupItemResponse> listGroupItem(@Param("groupId") Long groupId);
}
