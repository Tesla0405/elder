package com.qxy.elder.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveDto {
    private String name;
    private String username;
    private String password;
    private int role; //1-老人 0-普通用户（志愿者）

}
