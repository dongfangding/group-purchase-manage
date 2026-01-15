package com.ddf.group.purchase.core.mapper.ext;

import com.ddf.group.purchase.core.mapper.CommunityBaseMapper;
import com.ddf.group.purchase.core.model.entity.CommunityBase;
import java.util.List;

/**
* <p>小区基本信息维护</p >
*
* @author Snowball
* @version 1.0
* @date 2022/05/15 20:49
*/
public interface CommunityBaseExtMapper extends CommunityBaseMapper {

    /**
     * 根据小区名称模糊查询
     *
     * @param communityName
     * @return
     */
    List<CommunityBase> selectByCommunityNameLike(@Param("communityName") String communityName);
}
