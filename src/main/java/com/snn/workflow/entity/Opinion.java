package com.snn.workflow.entity;

import java.io.Serializable;
import java.util.Date;

public class Opinion implements Serializable {
    private Integer opinionid;

    private Integer projectid;

    private String projectname;

    private String state;

    private Date createTime;

    private Date updateTime;

    private String content;

    private static final long serialVersionUID = 1L;

    public Opinion(Integer opinionid, Integer projectid, String projectname, String state, Date createTime, Date updateTime, String content) {
        this.opinionid = opinionid;
        this.projectid = projectid;
        this.projectname = projectname;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.content = content;
    }

    public Opinion() {
        super();
    }

    public Integer getOpinionid() {
        return opinionid;
    }

    public void setOpinionid(Integer opinionid) {
        this.opinionid = opinionid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", opinionid=").append(opinionid);
        sb.append(", projectid=").append(projectid);
        sb.append(", projectname=").append(projectname);
        sb.append(", state=").append(state);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}