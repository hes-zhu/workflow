package com.snn.workflow.dao;

import com.snn.workflow.entity.TDict;
import java.util.List;

public interface TDictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDict record);

    TDict selectByPrimaryKey(Integer id);

    List<TDict> selectAll();

    int updateByPrimaryKey(TDict record);

    TDict selectAllByPID();
}