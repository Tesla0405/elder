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
public class CommentInfoDto {
    private Long id;
    private Long userId;
    private Long missionId;
    private String content;
    private Timestamp ctime;
    private Timestamp mtime;
}
