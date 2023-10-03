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
public class ElderInfoSaveVo {
    @ApiModelProperty("用户登录id,从session中取得 前端不需要传")
    private Long userId;
    @ApiModelProperty("老人姓名")
    private String name;
    @ApiModelProperty("老人性别 0-男 1-女")
    private Integer sex;
    @ApiModelProperty("老人生日 格式为yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp birth;
    @ApiModelProperty("老人身份证")
    private String idCard;
}
