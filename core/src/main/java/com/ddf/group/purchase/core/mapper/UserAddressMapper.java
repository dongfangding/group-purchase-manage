package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.UserAddress;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2026/02/04 19:16
 */
public interface UserAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    /**
     * 根据用户ID查询地址列表
     *
     * @param uid
     * @return
     */
    List<UserAddress> selectByUid(Long uid);

    /**
     * 获取用户默认收货地址
     *
     * @param uid
     * @return
     */
    UserAddress selectUserDefaultAddress(@Param("uid") Long uid);

    /**
     * 取消用户默认地址
     *
     * @param uid
     * @return
     */
    int cancelUserDefaultAddress(@Param("uid") Long uid);

    /**
     * 根据ID和用户ID删除
     *
     * @param id
     * @param uid
     * @return
     */
    int deleteByIdAndUid(@Param("id") Long id, @Param("uid") Long uid);
}
