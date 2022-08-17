package com.ddf.group.purchase.core.repository;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ddf.group.purchase.api.response.group.MyJoinGroupPageResponse;
import com.ddf.group.purchase.core.mapper.ext.UserJoinGroupInfoExtMapper;
import com.ddf.group.purchase.core.model.cqrs.group.MyJoinGroupQuery;
import com.ddf.group.purchase.core.model.entity.UserJoinGroupInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <p>用户参团仓储</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/07/17 21:12
 */
@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class UserJoinGroupInfoRepository {

    private final UserJoinGroupInfoExtMapper userJoinGroupInfoExtMapper;


    /**
     * 批量插入用户参团记录
     *
     * @param joins
     */
    public void batchInsertUserJoinGroup(List<UserJoinGroupInfo> joins) {
        userJoinGroupInfoExtMapper.batchInsert(joins);
    }

    /**
     * 我的参团列表查询
     *
     * @param query
     * @return
     */
    public List<MyJoinGroupPageResponse> myJoinGroup(MyJoinGroupQuery query) {
        return userJoinGroupInfoExtMapper.myJoinGroup(query);
    }


    /**
     * 查询团购下的参团用户id集合
     *
     * @param groupId
     * @return
     */
    public List<Long> selectJoinUids(Long groupId) {
        return userJoinGroupInfoExtMapper.selectJoinUids(groupId);
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
        final LambdaUpdateWrapper<UserJoinGroupInfo> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(UserJoinGroupInfo::getGroupPurchaseId, groupId)
                .eq(UserJoinGroupInfo::getJoinUid, joinUid);
        wrapper.set(UserJoinGroupInfo::getSubscribeProgress, subscribeFlag);
        return userJoinGroupInfoExtMapper.update(null, wrapper) > 0;
    }
}
