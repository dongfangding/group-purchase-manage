package com.ddf.group.purchase.core.converter;

import com.ddf.group.purchase.api.request.group.AddressDomain;
import com.ddf.group.purchase.api.request.group.SimulationPayRequest;
import com.ddf.group.purchase.api.request.user.UserAddressRequest;
import com.ddf.group.purchase.api.response.user.UserAddressResponse;
import com.ddf.group.purchase.core.model.entity.UserAddress;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * <p>user address 相关转换类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/24 19:53
 */
@Mapper
public interface UserAddressConvert {

    UserAddressConvert INSTANCE = Mappers.getMapper(UserAddressConvert.class);


    /**
     * 用户地址信息转换
     *
     * @param request
     * @return
     */
    @Mappings({
    })
    UserAddress convert(UserAddressRequest request);

    @Mappings({})
    UserAddressResponse convert(UserAddress userAddress);

    @Mappings({})
    List<UserAddressResponse> convert(List<UserAddress> userAddressList);

    @Mappings({})
    AddressDomain convert(SimulationPayRequest request);
}
