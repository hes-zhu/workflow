package com.snn.workflow.dao;

import com.snn.workflow.entity.TDict;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TDictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDict record);

    TDict selectByPrimaryKey(Integer id);

    List<TDict> selectAll();

    int updateByPrimaryKey(TDict record);
}