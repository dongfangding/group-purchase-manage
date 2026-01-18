package com.ddf.group.purchase.core.repository;

import com.ddf.boot.common.api.util.DateUtils;
import com.ddf.group.purchase.api.enume.GroupPurchaseItemJoinStatusEnum;
import com.ddf.group.purchase.api.response.group.MyJoinGroupPageResponse;
import com.ddf.group.purchase.core.mapper.GroupPurchaseItemMapper;
import com.ddf.group.purchase.core.model.cqrs.group.MyJoinGroupQuery;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseItem;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <p>团购明细商品</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/08/28 15:52
 */
@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class GroupPurchaseItemRepository {

    private final GroupPurchaseItemMapper groupPurchaseItemMapper;

    /**
     * 批量插入用户参团记录
     *
     * @param joins
     */
    public void batchInsertUserJoinGroup(List<GroupPurchaseItem> joins) {
        groupPurchaseItemMapper.batchInsert(joins);
    }

    /**
     * 我的参团列表查询
     *
     * @param query
     * @return
     */
    public List<MyJoinGroupPageResponse> myJoinGroup(MyJoinGroupQuery query) {
        return groupPurchaseItemMapper.myJoinGroup(query);
    }


    /**
     * 查询团购下的参团用户并且已支付id集合
     *
     * @param groupId
     * @return
     */
    public List<Long> selectJoinPaidUids(Long groupId) {
        return groupPurchaseItemMapper.selectJoinPaidUids(groupId);
    }

    /**
     * 设置用户参团订阅状态
     *
     * @param groupId
     * @param joinUid
     * @param subscribeFlag
     * @return
     */
    public boolean subscribe(Long groupId, Long joinUid, boolean subscribeFlag) {
        final GroupPurchaseItem item = new GroupPurchaseItem();
        item.setSubscribeProgress(subscribeFlag);
        item.setGroupPurchaseId(groupId);
        item.setJoinUid(joinUid);
        return groupPurchaseItemMapper.updateById(item) > 0;
    }

    /**
     * 关闭订单
     *
     * @param id
     * @return
     */
    public boolean closeOrder(Long id) {
        final GroupPurchaseItem item = new GroupPurchaseItem();
        item.setId(id);
        item.setJoinStatus(GroupPurchaseItemJoinStatusEnum.CLOSED.getValue());
        item.setPaidFlag(Boolean.FALSE);
        item.setStatusChangeTime(DateUtils.currentTimeSeconds());
        return groupPurchaseItemMapper.updateById(item) > 0;
    }
}
