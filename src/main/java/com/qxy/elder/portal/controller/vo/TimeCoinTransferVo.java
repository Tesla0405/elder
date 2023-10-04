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
public class TimeCoinTransferVo {
    @ApiModelProperty("时间币赠与目标用户id")
    private Long toUserId;
    @ApiModelProperty("时间币赠与数额")
    private Long timeCoin;
}
