package com.snn.workflow.dao;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.Projectinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Projectinfo record);

    Projectinfo selectByPrimaryKey(Integer id);

    ServiceResponse<Projectinfo> selectAll();

    int updateByPrimaryKey(Projectinfo record);

    int updateByProcessInstanceId(Projectinfo record);

    ServiceResponse<Projectinfo> getProjectByProInsId(@Param("ProcessInstanceId") Integer ProcessInstanceId);
}