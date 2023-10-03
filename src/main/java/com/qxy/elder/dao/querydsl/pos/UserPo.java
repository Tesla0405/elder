package com.qxy.elder.dao.querydsl.pos;

import javax.annotation.Generated;

/**
 * UserPo is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class UserPo {

    private java.sql.Timestamp ctime;

    private Long id;

    private Integer isDeleted;

    private java.sql.Timestamp mtime;

    private String name;

    private String password;

    private String username;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
         return "ctime = " + ctime + ", id = " + id + ", isDeleted = " + isDeleted + ", mtime = " + mtime + ", name = " + name + ", password = " + password + ", username = " + username;
    }

}

