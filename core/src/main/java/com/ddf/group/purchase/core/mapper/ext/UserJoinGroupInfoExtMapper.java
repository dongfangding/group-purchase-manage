package com.ddf.group.purchase.core.mapper.ext;

import com.ddf.group.purchase.api.response.group.MyJoinGroupPageResponse;
import com.ddf.group.purchase.core.mapper.UserJoinGroupInfoMapper;
import com.ddf.group.purchase.core.model.cqrs.group.MyJoinGroupQuery;
import com.ddf.group.purchase.core.model.entity.UserJoinGroupInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* <p>用户参加的团购</p >
*
* @author Snowball
* @version 1.0
* @date 2022/05/15 20:49
*/
public interface UserJoinGroupInfoExtMapper extends UserJoinGroupInfoMapper {
    /**
     * 批量插入
     * @param joins
     */
    void batchInsert(@Param("list") List<UserJoinGroupInfo> joins);

    /**
     * 我的参团列表查询
     *
     * @param query
     * @return
     */
    List<MyJoinGroupPageResponse> myJoinGroup(@Param("query") MyJoinGroupQuery query);

    /**
     * 查询团购下的参团用户id集合
     *
     * @param groupId
     * @return
     */
    List<Long> selectJoinUids(@Param("groupId") Long groupId);
}
