package com.snn.workflow.entity;

import java.io.Serializable;

public class Filetemplate implements Serializable {
    private Integer fileid;

    private String filename;

    private String projecttype;

    private String fileurl;

    private Boolean enable;

    private static final long serialVersionUID = 1L;

    public Filetemplate(Integer fileid, String filename, String projecttype, String fileurl, Boolean enable) {
        this.fileid = fileid;
        this.filename = filename;
        this.projecttype = projecttype;
        this.fileurl = fileurl;
        this.enable = enable;
    }

    public Filetemplate() {
        super();
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype == null ? null : projecttype.trim();
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl == null ? null : fileurl.trim();
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileid=").append(fileid);
        sb.append(", filename=").append(filename);
        sb.append(", projecttype=").append(projecttype);
        sb.append(", fileurl=").append(fileurl);
        sb.append(", enable=").append(enable);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}