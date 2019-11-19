package com.snn.workflow.dao;

import com.snn.workflow.entity.Opinion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionMapper {
    int deleteByPrimaryKey(Integer opinionid);

    int insert(Opinion record);

    Opinion selectByPrimaryKey(Integer opinionid);

    List<Opinion> selectAll();

    int updateByPrimaryKey(Opinion record);

    List<Opinion> selectByProjectId(@Param("projectId") Integer projectId);
}