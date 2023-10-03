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
public class VolunteerInfoVo {
    @ApiModelProperty("志愿者姓名")
    private String name;
    @ApiModelProperty("志愿者性别")
    private Integer sex;
    @ApiModelProperty("志愿者生日 格式为yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp birth;
    @ApiModelProperty("志愿者身份证")
    private String idCard;
    @ApiModelProperty("志愿者标签")
    private String tags;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp ctime;
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp mtime;
}
