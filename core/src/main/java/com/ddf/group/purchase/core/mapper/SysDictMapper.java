package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.SysDict;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2026/02/04 19:16
 */
public interface SysDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    SysDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);

    /**
     * 根据字典类型代码查询字典列表
     *
     * @param dictTypeCode
     * @return
     */
    List<SysDict> selectListByDictTypeCode(@Param("dictTypeCode") String dictTypeCode);
}
