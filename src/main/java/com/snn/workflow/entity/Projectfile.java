package com.snn.workflow.entity;

import java.io.Serializable;
import java.util.Date;

public class Projectfile implements Serializable {
    private Integer id;

    private Integer projectid;

    private String url;

    private Date createtime;

    private Date updatetime;

    private Integer state;

    private static final long serialVersionUID = 1L;

    public Projectfile(Integer id, Integer projectid, String url, Date createtime, Date updatetime, Integer state) {
        this.id = id;
        this.projectid = projectid;
        this.url = url;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.state = state;
    }

    public Projectfile() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectid=").append(projectid);
        sb.append(", url=").append(url);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}