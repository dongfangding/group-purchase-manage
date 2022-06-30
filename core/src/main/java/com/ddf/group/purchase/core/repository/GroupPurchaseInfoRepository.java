package com.ddf.group.purchase.core.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ddf.group.purchase.api.request.group.MyInitiatedGroupPageRequest;
import com.ddf.group.purchase.core.mapper.GroupPurchaseInfoMapper;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseInfo;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/06/29 09:55
 */
@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class GroupPurchaseInfoRepository {

    private final GroupPurchaseInfoMapper groupPurchaseInfoMapper;

    /**
     * 团购列表查询
     *
     * @param request
     * @return
     */
    public List<GroupPurchaseInfo> listGroupPurchaseInfo(MyInitiatedGroupPageRequest request) {
        final LambdaQueryWrapper<GroupPurchaseInfo> wrapper = Wrappers.lambdaQuery();
        if (Objects.nonNull(request.getGroupMasterUid())) {
            wrapper.eq(GroupPurchaseInfo::getGroupMasterUid, request.getGroupMasterUid());
        }
        wrapper.likeLeft(GroupPurchaseInfo::getName, request.getGroupName());
        return groupPurchaseInfoMapper.selectList(wrapper);
    }
}
