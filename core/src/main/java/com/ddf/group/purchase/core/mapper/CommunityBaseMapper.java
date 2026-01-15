package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.CommunityBase;
import java.util.List;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/08/20 20:25
 */
public interface CommunityBaseMapper {

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
     * 插入
     *
     * @param communityBase
     * @return
     */
    int insert(CommunityBase communityBase);

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
}
