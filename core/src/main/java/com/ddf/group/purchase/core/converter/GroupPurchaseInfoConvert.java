package com.ddf.group.purchase.core.converter;

import com.ddf.group.purchase.api.request.group.MyInitiatedGroupPageRequest;
import com.ddf.group.purchase.api.request.group.MyJoinGroupPageRequest;
import com.ddf.group.purchase.api.response.group.MyInitiatedGroupPageResponse;
import com.ddf.group.purchase.core.model.cqrs.group.MyInitiatedGroupQuery;
import com.ddf.group.purchase.core.model.cqrs.group.MyJoinGroupQuery;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseInfo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>团购对象转换器</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/06/29 10:05
 */
@Mapper
public interface GroupPurchaseInfoConvert {

    GroupPurchaseInfoConvert INSTANCE = Mappers.getMapper(GroupPurchaseInfoConvert.class);

    /**
     * 团购主表转响应类
     *
     * @param groupPurchaseInfo
     * @return
     */
//    GroupPurchaseInfoPageResponse convert(GroupPurchaseInfo groupPurchaseInfo);

    /**
     * 团购主表转响应类
     *
     * @param groupPurchaseInfo
     * @return
     */
    List<MyInitiatedGroupPageResponse> convert(List<GroupPurchaseInfo> groupPurchaseInfo);


    /**
     *
     *
     * @param request
     * @return
     */
    @Mapping(target = "groupMasterUid", expression = "java(com.ddf.boot.common.authentication.util.UserContextUtil.getLongUserId())")
    MyInitiatedGroupQuery convert(MyInitiatedGroupPageRequest request);

    /**
     *
     *
     * @param request
     * @return
     */
    @Mapping(target = "joinUid", expression = "java(com.ddf.boot.common.authentication.util.UserContextUtil.getLongUserId())")
    MyJoinGroupQuery convert(MyJoinGroupPageRequest request);

}
