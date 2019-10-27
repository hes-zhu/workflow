package com.snn.workflow.dao;

import com.snn.workflow.entity.WxProjectItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxProjectItemMapper {
    int deleteByPrimaryKey(Integer projectid);

    int insert(WxProjectItem record);

    WxProjectItem selectByPrimaryKey(Integer projectid);

    List<WxProjectItem> selectAll();

    int updateByPrimaryKey(WxProjectItem record);

    int updateByProcessInstanceId(WxProjectItem record);

    List<WxProjectItem> getProjectByProInsId(@Param("ProcessInstanceId") Integer ProcessInstanceId);
}