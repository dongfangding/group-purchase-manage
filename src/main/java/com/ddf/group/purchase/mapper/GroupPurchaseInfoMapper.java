package com.ddf.group.purchase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddf.group.purchase.model.entity.GroupPurchaseInfo;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/23 23:11
 */
public interface GroupPurchaseInfoMapper extends BaseMapper<GroupPurchaseInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(GroupPurchaseInfo record);

    int insertSelective(GroupPurchaseInfo record);

    GroupPurchaseInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupPurchaseInfo record);

    int updateByPrimaryKey(GroupPurchaseInfo record);
}