package com.ddf.group.purchase.core.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ddf.group.purchase.core.mapper.UserAddressMapper;
import com.ddf.group.purchase.core.model.entity.UserAddress;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>用户地址仓储</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/06 20:32
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserAddressRepository {

    private final UserAddressMapper userAddressMapper;

    /**
     * 查询用户的收货地址
     *
     * @param userId
     * @return
     */
    public List<UserAddress> listUserAddress(Long userId) {
        final LambdaQueryWrapper<UserAddress> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserAddress::getUid, userId);
        return userAddressMapper.selectList(wrapper);
    }


    /**
     * 取消用户收货地址的默认地址设置
     *
     * @param userId
     */
    public void cancelUserDefaultAddress(Long userId) {
        final LambdaUpdateWrapper<UserAddress> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(UserAddress::getUid, userId)
                .eq(UserAddress::getDefaultFlag, Boolean.TRUE);
        wrapper.set(UserAddress::getDefaultFlag, Boolean.FALSE);
        userAddressMapper.update(null, wrapper);
    }

    /**
     * 获取用户默认收货地址
     *
     * @param userId
     * @return
     */
    public UserAddress getUserDefaultAddress(Long userId) {
        final LambdaQueryWrapper<UserAddress> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserAddress::getUid, userId)
                .eq(UserAddress::getDefaultFlag, Boolean.TRUE)
                .orderByDesc(UserAddress::getId);
        return userAddressMapper.selectOne(wrapper);
    }

    /**
     * 删除用户的收货地址
     *
     * @param id
     * @param userId
     * @return
     */
    public int deleteUserAddress(Long id, Long userId) {
        final LambdaUpdateWrapper<UserAddress> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(UserAddress::getId, id)
                .eq(UserAddress::getUid, userId);
        return userAddressMapper.delete(wrapper);
    }
}
