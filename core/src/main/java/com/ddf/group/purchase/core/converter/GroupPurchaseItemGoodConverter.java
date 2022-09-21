package com.ddf.group.purchase.core.converter;

import com.ddf.group.purchase.api.response.group.GroupPurchaseItemGoodResponse;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseItemGood;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/21 16:01
 */
@Mapper
public interface GroupPurchaseItemGoodConverter {

    GroupPurchaseItemGoodConverter INSTANCE = Mappers.getMapper(GroupPurchaseItemGoodConverter.class);

    @Mappings({
    })
    GroupPurchaseItemGoodResponse convert(GroupPurchaseItemGood itemGood);
}
