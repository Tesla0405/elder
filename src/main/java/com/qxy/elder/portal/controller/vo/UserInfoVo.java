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
public class UserInfoVo {
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户昵称")
    private String name;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户角色")
    private Integer role;
    @ApiModelProperty("用户角色描述 0-志愿者 1-老人")
    private String roleDesc;
}
