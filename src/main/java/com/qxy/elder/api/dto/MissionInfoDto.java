package com.qxy.elder.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MissionInfoDto {
    private Long id;
    private Long publishUserId;
    private Long accomplishUserId;
    private String name;
    private String content;
    private String tags;
    private Long timeCoinPrice;
    private Integer missionStatus;
    private String missionStatusDesc;
    private Timestamp ctime;
    private Timestamp mtime;
}
