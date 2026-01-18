package com.ddf.group.purchase.core.repository;

import com.ddf.group.purchase.core.mapper.CommunityBaseMapper;
import com.ddf.group.purchase.core.model.entity.CommunityBase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>小区仓储</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/29 22:38
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class CommunityBaseRepository {

    private final CommunityBaseMapper communityBaseMapper;

    /**
     * 根据id返回小区信息
     *
     * @param id
     * @return
     */
    public CommunityBase getById(Long id) {
        return communityBaseMapper.selectById(id);
    }

    /**
     * 根据小区名称模糊查询
     *
     * @param communityName
     * @return
     */
    public List<CommunityBase> selectByCommunityNameLike(String communityName) {
        return communityBaseMapper.selectByCommunityNameLike(communityName);
    }
}
