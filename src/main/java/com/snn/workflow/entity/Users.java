package com.snn.workflow.entity;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {
    private Integer id;

    private String name;

    private String phone;

    private String email;

    private String telephone;

    private String address;

    private Boolean enabled;

    private String username;

    private String password;

    private String question;

    private String answer;

    private String userface;

    private String remark;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Users(Integer id, String name, String phone, String email, String telephone, String address, Boolean enabled, String username, String password, String question, String answer, String userface, String remark, Date createtime, Date updatetime) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.enabled = enabled;
        this.username = username;
        this.password = password;
        this.question = question;
        this.answer = answer;
        this.userface = userface;
        this.remark = remark;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Users() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface == null ? null : userface.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", telephone=").append(telephone);
        sb.append(", address=").append(address);
        sb.append(", enabled=").append(enabled);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", question=").append(question);
        sb.append(", answer=").append(answer);
        sb.append(", userface=").append(userface);
        sb.append(", remark=").append(remark);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}