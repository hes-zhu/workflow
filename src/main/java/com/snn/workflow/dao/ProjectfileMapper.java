package com.snn.workflow.dao;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.Projectfile;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectfileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Projectfile record);

    Projectfile selectByPrimaryKey(Integer id);

    ServiceResponse<Projectfile> selectAll();

    int updateByPrimaryKey(Projectfile record);
}