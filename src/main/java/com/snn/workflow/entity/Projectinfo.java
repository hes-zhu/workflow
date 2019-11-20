package com.snn.workflow.entity;

import java.io.Serializable;
import java.util.Date;

public class Projectinfo implements Serializable {
    private Integer id;

    private Integer processinstanceid;

    private String name;

    private String type;

    private Float budget;

    private String property;

    private String unit;

    private String time;

    private Integer state;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Projectinfo(Integer id, Integer processinstanceid, String name, String type, Float budget, String property, String unit, String time, Integer state, Date createtime, Date updatetime) {
        this.id = id;
        this.processinstanceid = processinstanceid;
        this.name = name;
        this.type = type;
        this.budget = budget;
        this.property = property;
        this.unit = unit;
        this.time = time;
        this.state = state;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Projectinfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcessinstanceid() {
        return processinstanceid;
    }

    public void setProcessinstanceid(Integer processinstanceid) {
        this.processinstanceid = processinstanceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Float getBudget() {
        return budget;
    }

    public void setBudget(Float budget) {
        this.budget = budget;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property == null ? null : property.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", processinstanceid=").append(processinstanceid);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", budget=").append(budget);
        sb.append(", property=").append(property);
        sb.append(", unit=").append(unit);
        sb.append(", time=").append(time);
        sb.append(", state=").append(state);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}