package com.qxy.elder.portal.controller.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveVo {
    private Long id;
    private String name;
    private String username;
    private String password;
}
