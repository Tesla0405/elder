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
public class ElderInfoDto {
    private Long id;
    private String name;
    private Integer sex;
    private Timestamp birth;
    private String idCard;
    private Timestamp ctime;
    private Timestamp mtime;
}
