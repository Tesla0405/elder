package com.qxy.elder.dao.querydsl.pos;

import javax.annotation.Generated;

/**
 * MissionPo is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class MissionPo {

    private Long accomplishUserId;

    private String content;

    private java.sql.Timestamp ctime;

    private Long id;

    private Integer isDeleted;

    private Integer missionStatus;

    private java.sql.Timestamp mtime;

    private String name;

    private Long publishUserId;

    private String tags;

    private Long timeCoinPrice;

    public Long getAccomplishUserId() {
        return accomplishUserId;
    }

    public void setAccomplishUserId(Long accomplishUserId) {
        this.accomplishUserId = accomplishUserId;
    }

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

    public Integer getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(Integer missionStatus) {
        this.missionStatus = missionStatus;
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

    public Long getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(Long publishUserId) {
        this.publishUserId = publishUserId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getTimeCoinPrice() {
        return timeCoinPrice;
    }

    public void setTimeCoinPrice(Long timeCoinPrice) {
        this.timeCoinPrice = timeCoinPrice;
    }

    @Override
    public String toString() {
         return "accomplishUserId = " + accomplishUserId + ", content = " + content + ", ctime = " + ctime + ", id = " + id + ", isDeleted = " + isDeleted + ", missionStatus = " + missionStatus + ", mtime = " + mtime + ", name = " + name + ", publishUserId = " + publishUserId + ", tags = " + tags + ", timeCoinPrice = " + timeCoinPrice;
    }

}

