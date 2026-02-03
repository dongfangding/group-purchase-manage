package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.CommunityBase;
import java.util.List;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2026/02/03 23:45
 */
public interface CommunityBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommunityBase record);

    int insertSelective(CommunityBase record);

    CommunityBase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommunityBase record);

    int updateByPrimaryKey(CommunityBase record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    CommunityBase selectById(Long id);

    /**
     * 查询所有
     *
     * @return
     */
    List<CommunityBase> selectList();

    /**
     * 根据ID更新
     *
     * @param communityBase
     * @return
     */
    int updateById(CommunityBase communityBase);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据小区名称模糊查询
     *
     * @param communityName
     * @return
     */
    List<CommunityBase> selectByCommunityNameLike(String communityName);
}