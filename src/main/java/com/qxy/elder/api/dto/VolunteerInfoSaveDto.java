package com.qxy.elder.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VolunteerInfoSaveDto {
    private Long userId;
    private String name;
    private Integer sex;
    private Timestamp birth;
    private String idCard;
    private String tags;
}
