package com.ddf.group.purchase.core.repository;

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
        return userAddressMapper.selectByUid(userId);
    }


    /**
     * 取消用户收货地址的默认地址设置
     *
     * @param userId
     */
    public void cancelUserDefaultAddress(Long userId) {
        userAddressMapper.cancelUserDefaultAddress(userId);
    }

    /**
     * 获取用户默认收货地址
     *
     * @param userId
     * @return
     */
    public UserAddress getUserDefaultAddress(Long userId) {
        return userAddressMapper.selectUserDefaultAddress(userId);
    }

    /**
     * 删除用户的收货地址
     *
     * @param id
     * @param userId
     * @return
     */
    public int deleteUserAddress(Long id, Long userId) {
        return userAddressMapper.deleteByIdAndUid(id, userId);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public UserAddress getById(Long id) {
        return userAddressMapper.selectByPrimaryKey(id);
    }

    /**
     * 插入
     *
     * @param userAddress
     * @return
     */
    public int insert(UserAddress userAddress) {
        return userAddressMapper.insert(userAddress);
    }

    /**
     * 更新
     *
     * @param userAddress
     * @return
     */
    public int update(UserAddress userAddress) {
        return userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }
}
