package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.SysDict;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2026/02/03 23:45
 */
public interface SysDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    SysDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    SysDict selectById(Long id);

    /**
     * 查询所有
     *
     * @return
     */
    List<SysDict> selectList();

    /**
     * 根据字典类型代码查询字典列表
     *
     * @param dictTypeCode
     * @return
     */
    List<SysDict> selectListByDictTypeCode(@Param("dictTypeCode") String dictTypeCode);

    /**
     * 根据ID更新
     *
     * @param sysDict
     * @return
     */
    int updateById(SysDict sysDict);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);
}