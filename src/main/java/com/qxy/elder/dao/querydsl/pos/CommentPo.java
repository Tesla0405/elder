package com.qxy.elder.dao.querydsl.pos;

import javax.annotation.Generated;

/**
 * CommentPo is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class CommentPo {

    private String content;

    private java.sql.Timestamp ctime;

    private Long id;

    private Integer isDeleted;

    private Long missionId;

    private java.sql.Timestamp mtime;

    private Long userId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public java.sql.Timestamp getMtime() {
        return mtime;
    }

    public void setMtime(java.sql.Timestamp mtime) {
        this.mtime = mtime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
         return "content = " + content + ", ctime = " + ctime + ", id = " + id + ", isDeleted = " + isDeleted + ", missionId = " + missionId + ", mtime = " + mtime + ", userId = " + userId;
    }

}

