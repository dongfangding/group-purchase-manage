package com.ddf.group.purchase.core.converter;

import com.ddf.group.purchase.api.request.group.CustomizeCreateRequest;
import com.ddf.group.purchase.api.request.group.MyInitiatedGroupPageRequest;
import com.ddf.group.purchase.api.request.group.MyJoinGroupPageRequest;
import com.ddf.group.purchase.api.response.group.GroupPurchaseTraceDTO;
import com.ddf.group.purchase.api.response.group.MyInitiatedGroupPageResponse;
import com.ddf.group.purchase.core.model.cqrs.group.GroupListQuery;
import com.ddf.group.purchase.core.model.cqrs.group.MyInitiatedGroupQuery;
import com.ddf.group.purchase.core.model.cqrs.group.MyJoinGroupQuery;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseGood;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseInfo;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseTrace;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
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
     * 自定义创建团购请求参数类转换团购主表
     *
     * @param request
     * @return
     */
    @Mappings({
    })
    GroupPurchaseInfo convertGroup(CustomizeCreateRequest request);

    /**
     * 自定义创建团购请求参数类转换团购商品信息
     *
     * @param request
     * @return
     */
    @Mappings({
    })
    GroupPurchaseGood convertGood(CustomizeCreateRequest request);

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
    @Mappings({
    })
    List<MyInitiatedGroupPageResponse> convert(List<GroupPurchaseInfo> groupPurchaseInfo);


    /**
     *
     *
     * @param request
     * @return
     */
    @Mapping(target = "groupMasterUid", expression = "java(com.ddf.boot.common.authentication.util.UserContextUtil.getLongUserId())")
    MyInitiatedGroupQuery convertToMyInitiatedGroupQuery(MyInitiatedGroupPageRequest request);

    @Mapping(target = "groupMasterUid", expression = "java(com.ddf.boot.common.authentication.util.UserContextUtil.getLongUserId())")
    GroupListQuery convert(MyInitiatedGroupPageRequest request);

    /**
     *
     *
     * @param request
     * @return
     */
    @Mapping(target = "joinUid", expression = "java(com.ddf.boot.common.authentication.util.UserContextUtil.getLongUserId())")
    MyJoinGroupQuery convert(MyJoinGroupPageRequest request);

    @Mappings({
    })
    GroupPurchaseTraceDTO convertTrace(GroupPurchaseTrace trace);

    @Mappings({
    })
    List<GroupPurchaseTraceDTO> convertTrace(List<GroupPurchaseTrace> trace);
}
