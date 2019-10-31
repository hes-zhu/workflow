package com.snn.workflow.entity;

import java.io.Serializable;

public class TDict implements Serializable {
    private Integer id;

    private String dictcode;

    private String dictchname;

    private String stdcode;

    private String dictdecipt;

    private Integer pid;

    private Integer ordercode;

    private String remarks;

    private String status;

    private String natureMark;

    private String enablemark;

    private static final long serialVersionUID = 1L;

    public TDict(Integer id, String dictcode, String dictchname, String stdcode, String dictdecipt, Integer pid, Integer ordercode, String remarks, String status, String natureMark, String enablemark) {
        this.id = id;
        this.dictcode = dictcode;
        this.dictchname = dictchname;
        this.stdcode = stdcode;
        this.dictdecipt = dictdecipt;
        this.pid = pid;
        this.ordercode = ordercode;
        this.remarks = remarks;
        this.status = status;
        this.natureMark = natureMark;
        this.enablemark = enablemark;
    }

    public TDict() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictcode() {
        return dictcode;
    }

    public void setDictcode(String dictcode) {
        this.dictcode = dictcode == null ? null : dictcode.trim();
    }

    public String getDictchname() {
        return dictchname;
    }

    public void setDictchname(String dictchname) {
        this.dictchname = dictchname == null ? null : dictchname.trim();
    }

    public String getStdcode() {
        return stdcode;
    }

    public void setStdcode(String stdcode) {
        this.stdcode = stdcode == null ? null : stdcode.trim();
    }

    public String getDictdecipt() {
        return dictdecipt;
    }

    public void setDictdecipt(String dictdecipt) {
        this.dictdecipt = dictdecipt == null ? null : dictdecipt.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(Integer ordercode) {
        this.ordercode = ordercode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getNatureMark() {
        return natureMark;
    }

    public void setNatureMark(String natureMark) {
        this.natureMark = natureMark == null ? null : natureMark.trim();
    }

    public String getEnablemark() {
        return enablemark;
    }

    public void setEnablemark(String enablemark) {
        this.enablemark = enablemark == null ? null : enablemark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dictcode=").append(dictcode);
        sb.append(", dictchname=").append(dictchname);
        sb.append(", stdcode=").append(stdcode);
        sb.append(", dictdecipt=").append(dictdecipt);
        sb.append(", pid=").append(pid);
        sb.append(", ordercode=").append(ordercode);
        sb.append(", remarks=").append(remarks);
        sb.append(", status=").append(status);
        sb.append(", natureMark=").append(natureMark);
        sb.append(", enablemark=").append(enablemark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}