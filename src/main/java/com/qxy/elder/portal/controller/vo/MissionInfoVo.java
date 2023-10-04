package com.qxy.elder.portal.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MissionInfoVo {
    @ApiModelProperty("任务id")
    private Long id;
    @ApiModelProperty("任务发布用户id")
    private Long publishUserId;
    @ApiModelProperty("任务执行id")
    private Long accomplishUserId;
    @ApiModelProperty("任务名称")
    private String name;
    @ApiModelProperty("任务内容")
    private String content;
    @ApiModelProperty("任务标签")
    private String tags;
    @ApiModelProperty("任务时间币")
    private Long timeCoinPrice;
    @ApiModelProperty("任务状态")
    private Integer missionStatus;
    @ApiModelProperty("任务状态描述")
    private String missionStatusDesc;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp ctime;
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp mtime;
}
