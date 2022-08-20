package com.ddf.group.purchase.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseInfo;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/08/20 21:13
 */
public interface GroupPurchaseInfoMapper extends BaseMapper<GroupPurchaseInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(GroupPurchaseInfo record);

    int insertSelective(GroupPurchaseInfo record);

    GroupPurchaseInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupPurchaseInfo record);

    int updateByPrimaryKey(GroupPurchaseInfo record);
}