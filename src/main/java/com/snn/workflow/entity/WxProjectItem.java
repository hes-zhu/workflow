package com.snn.workflow.entity;

import java.io.Serializable;

public class WxProjectItem implements Serializable {
    private Integer projectid;

    private String processdefid;

    private String projectname;

    private String projecttype;

    private Float projectbudget;

    private Integer processinstanceid;

    private static final long serialVersionUID = 1L;

    public WxProjectItem(Integer projectid, String processdefid, String projectname, String projecttype, Float projectbudget, Integer processinstanceid) {
        this.projectid = projectid;
        this.processdefid = processdefid;
        this.projectname = projectname;
        this.projecttype = projecttype;
        this.projectbudget = projectbudget;
        this.processinstanceid = processinstanceid;
    }

    public WxProjectItem() {
        super();
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getProcessdefid() {
        return processdefid;
    }

    public void setProcessdefid(String processdefid) {
        this.processdefid = processdefid == null ? null : processdefid.trim();
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype == null ? null : projecttype.trim();
    }

    public Float getProjectbudget() {
        return projectbudget;
    }

    public void setProjectbudget(Float projectbudget) {
        this.projectbudget = projectbudget;
    }

    public Integer getProcessinstanceid() {
        return processinstanceid;
    }

    public void setProcessinstanceid(Integer processinstanceid) {
        this.processinstanceid = processinstanceid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projectid=").append(projectid);
        sb.append(", processdefid=").append(processdefid);
        sb.append(", projectname=").append(projectname);
        sb.append(", projecttype=").append(projecttype);
        sb.append(", projectbudget=").append(projectbudget);
        sb.append(", processinstanceid=").append(processinstanceid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}