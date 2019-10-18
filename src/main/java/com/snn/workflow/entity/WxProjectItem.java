package com.snn.workflow.entity;

import java.io.Serializable;

public class WxProjectItem implements Serializable {
    private Integer id;

    private String projectname;

    private String projecttype;

    private Float projectbudget;

    private static final long serialVersionUID = 1L;

    public WxProjectItem(Integer id, String projectname, String projecttype, Float projectbudget) {
        this.id = id;
        this.projectname = projectname;
        this.projecttype = projecttype;
        this.projectbudget = projectbudget;
    }

    public WxProjectItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectname=").append(projectname);
        sb.append(", projecttype=").append(projecttype);
        sb.append(", projectbudget=").append(projectbudget);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}