package com.qxy.elder.dao.querydsl.pos;

import javax.annotation.Generated;

/**
 * TestPo is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class TestPo {

    private java.sql.Timestamp ctime;

    private Long id;

    private Integer isDeleted;

    private java.sql.Timestamp mtime;

    private String name;

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

    @Override
    public String toString() {
         return "ctime = " + ctime + ", id = " + id + ", isDeleted = " + isDeleted + ", mtime = " + mtime + ", name = " + name;
    }

}

