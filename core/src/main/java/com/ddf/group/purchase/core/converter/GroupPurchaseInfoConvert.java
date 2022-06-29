package com.ddf.group.purchase.core.converter;

import com.ddf.group.purchase.api.response.group.GroupPurchaseInfoPageResponse;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseInfo;
import java.util.List;
import org.mapstruct.Mapper;
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
    List<GroupPurchaseInfoPageResponse> convert(List<GroupPurchaseInfo> groupPurchaseInfo);

}
