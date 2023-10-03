package com.qxy.elder.dao.querydsl.pos;

import javax.annotation.Generated;

/**
 * VolunteerPo is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class VolunteerPo {

    private java.sql.Date birth;

    private java.sql.Timestamp ctime;

    private Long id;

    private String idCard;

    private Integer isDeleted;

    private java.sql.Timestamp mtime;

    private String name;

    private Integer sex;

    private String tags;

    private Long userId;

    public java.sql.Date getBirth() {
        return birth;
    }

    public void setBirth(java.sql.Date birth) {
        this.birth = birth;
    }

    public java.sql.Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(java.sql.Timestamp ctime) {
        this.ctime = ctime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public java.sql.Timestamp getMtime() {
        return mtime;
    }

    public void setMtime(java.sql.Timestamp mtime) {
        this.mtime = mtime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
         return "birth = " + birth + ", ctime = " + ctime + ", id = " + id + ", idCard = " + idCard + ", isDeleted = " + isDeleted + ", mtime = " + mtime + ", name = " + name + ", sex = " + sex + ", tags = " + tags + ", userId = " + userId;
    }

}

