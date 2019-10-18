package com.snn.workflow.dao;

import com.snn.workflow.entity.WxProjectItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WxProjectItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxProjectItem record);

    WxProjectItem selectByPrimaryKey(Integer id);

    List<WxProjectItem> selectAll();

    int updateByPrimaryKey(WxProjectItem record);
}