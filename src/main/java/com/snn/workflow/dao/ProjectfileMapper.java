package com.snn.workflow.dao;

import com.snn.workflow.entity.Projectfile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectfileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Projectfile record);

    Projectfile selectByPrimaryKey(Integer id);

    List<Projectfile> selectAll();

    int updateByPrimaryKey(Projectfile record);

    List<Projectfile> selectByProjectId(@Param("projectId") Integer projectId);
}