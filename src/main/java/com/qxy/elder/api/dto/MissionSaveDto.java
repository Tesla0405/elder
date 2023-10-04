package com.qxy.elder.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MissionSaveDto {
    private Long id;
    private Long publishUserId;
    private String name;
    private String content;
    private String tags;
    private Long timeCoinPrice;
}
