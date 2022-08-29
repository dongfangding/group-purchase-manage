package com.ddf.group.purchase.core.converter;

import com.ddf.group.purchase.api.response.common.SysDictResponse;
import com.ddf.group.purchase.core.model.entity.SysDict;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/08/29 16:57
 */
@Mapper
public interface CommonConverter {

    CommonConverter INSTANCE = Mappers.getMapper(CommonConverter.class);


    SysDictResponse convert(SysDict sysDict);

    List<SysDictResponse> convert(List<SysDict> sysDictList);
}
