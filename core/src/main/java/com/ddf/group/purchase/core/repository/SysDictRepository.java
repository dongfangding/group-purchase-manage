package com.ddf.group.purchase.core.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ddf.group.purchase.core.mapper.SysDictMapper;
import com.ddf.group.purchase.core.model.entity.SysDict;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>字典仓储</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/08/29 16:38
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class SysDictRepository {

    private final SysDictMapper sysDictMapper;

    private final LoadingCache<String, List<SysDict>> DICT_CACHE = Caffeine.newBuilder()
            .weakKeys()
            .weakValues()
            .initialCapacity(50)
            .maximumSize(200)
            .expireAfterWrite(Duration.ofHours(1))
            .build(this::listDictByCode);

    public void clearCache() {
        DICT_CACHE.invalidateAll();
    }


    /**
     * 从缓存中获取字典数据
     *
     * @param dictCode
     * @return
     */
    public List<SysDict> listDictByCodeFromCache(String dictCode) {
        return DICT_CACHE.get(dictCode);
    }


    /**
     * 根据字典代码查询字典数据
     *
     * @param dictCode
     * @return
     */
    public List<SysDict> listDictByCode(String dictCode) {
        final LambdaQueryWrapper<SysDict> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysDict::getDictTypeCode, dictCode)
                .eq(SysDict::getActive, true)
                .orderByAsc(SysDict::getSort);
        return sysDictMapper.selectList(wrapper);
    }
}
