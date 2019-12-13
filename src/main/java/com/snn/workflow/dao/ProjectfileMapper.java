package com.snn.workflow.dao;

import com.snn.workflow.entity.Projectfile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectfileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Projectfile record);

    Projectfile selectByPrimaryKey(Integer id);

    List<Projectfile> selectAll();

    int updateByPrimaryKey(Projectfile record);

    List<Projectfile> selectByProjectId(@Param("projectId") Integer projectId);

    int updateFileState(@Param("id") Integer id);

}