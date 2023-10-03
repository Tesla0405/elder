package com.qxy.elder.portal.util;

import com.qxy.elder.api.dto.LoginDto;
import com.qxy.elder.api.dto.UserDto;
import com.qxy.elder.api.dto.UserSaveDto;
import com.qxy.elder.portal.controller.vo.LoginVo;
import com.qxy.elder.portal.controller.vo.UserInfoVo;
import com.qxy.elder.portal.controller.vo.UserSaveVo;

public class MappingUtil {

    public static UserSaveDto convertUserSaveVo2Dto(UserSaveVo saveVo) {
        return UserSaveDto.builder()
                .name(saveVo.getName())
                .username(saveVo.getUsername())
                .password(saveVo.getPassword())
                .build();
    }

    public static LoginDto convertLoginVo2Dto(LoginVo loginVo) {
        return LoginDto.builder()
                .username(loginVo.getUsername())
                .password(loginVo.getPassword())
                .build();
    }

//    UserInfoVo UserDto
      public static UserInfoVo convertUserDto2Vo(UserDto userDto){
        return UserInfoVo.builder()
                .name(userDto.getName())
                .id(userDto.getId())
                .username(userDto.getUsername())
                .build();
      }

}
