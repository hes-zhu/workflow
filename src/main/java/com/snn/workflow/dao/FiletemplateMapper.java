package com.snn.workflow.dao;

import com.snn.workflow.entity.Filetemplate;
import java.util.List;

public interface FiletemplateMapper {
    int deleteByPrimaryKey(Integer fileid);

    int insert(Filetemplate record);

    Filetemplate selectByPrimaryKey(Integer fileid);

    List<Filetemplate> selectAll();

    int updateByPrimaryKey(Filetemplate record);
}