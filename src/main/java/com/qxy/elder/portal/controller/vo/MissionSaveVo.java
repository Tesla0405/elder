package com.qxy.elder.portal.controller.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MissionSaveVo {
    @ApiModelProperty("任务id 新建时为空")
    private Long id;
    @ApiModelProperty("任务名称")
    private String name;
    @ApiModelProperty("任务内容")
    private String content;
    @ApiModelProperty("任务标签")
    private String tags;
    @ApiModelProperty("任务时间币")
    private Long timeCoinPrice;
}
