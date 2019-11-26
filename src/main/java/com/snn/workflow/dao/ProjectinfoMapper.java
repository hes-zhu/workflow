package com.snn.workflow.dao;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.Projectinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Projectinfo record);

    Projectinfo selectByPrimaryKey(Integer id);

    List<Projectinfo> selectAll();

    int updateByPrimaryKey(Projectinfo record);

    int updateByProcessInstanceId(Projectinfo record);

    Projectinfo getProjectByProInsId(@Param("ProcessInstanceId") Integer ProcessInstanceId);

    int updateProjectState(@Param("id") Integer id, @Param("state") Integer state);
}