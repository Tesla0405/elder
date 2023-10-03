package com.qxy.elder.portal.util;

import com.qxy.elder.api.dto.*;
import com.qxy.elder.enums.UserRoleEnum;
import com.qxy.elder.portal.controller.vo.*;

import java.util.Objects;

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

      public static UserInfoVo convertUserDto2Vo(UserDto userDto){
        return UserInfoVo.builder()
                .name(userDto.getName())
                .id(userDto.getId())
                .username(userDto.getUsername())
                .role(userDto.getRole())
                .roleDesc(UserRoleEnum.getByCode(userDto.getRole()).getDesc())
                .build();
      }

      public static ElderInfoSaveDto convertElderInfoSaveVo2Dto(ElderInfoSaveVo saveVo) {
        return ElderInfoSaveDto.builder()
                .userId(saveVo.getUserId())
                .name(saveVo.getName())
                .sex(saveVo.getSex())
                .birth(saveVo.getBirth())
                .idCard(saveVo.getIdCard())
                .build();
      }

      public static ElderInfoVo convertElderInfoDto2Vo(ElderInfoDto infoDto) {
        if (Objects.isNull(infoDto)) {
            return null;
        }
        return ElderInfoVo.builder()
                .name(infoDto.getName())
                .sex(infoDto.getSex())
                .birth(infoDto.getBirth())
                .idCard(infoDto.getIdCard())
                .ctime(infoDto.getCtime())
                .mtime(infoDto.getMtime())
                .build();
      }

      public static VolunteerInfoSaveDto convertVolunteerInfoSaveVo2Dto(VolunteerInfoSaveVo saveVo) {
        return VolunteerInfoSaveDto.builder()
                .userId(saveVo.getUserId())
                .name(saveVo.getName())
                .sex(saveVo.getSex())
                .birth(saveVo.getBirth())
                .idCard(saveVo.getIdCard())
                .tags(saveVo.getTags())
                .build();
      }

    public static VolunteerInfoVo convertVolunteerInfoDto2Vo(VolunteerInfoDto infoDto) {
        if (Objects.isNull(infoDto)) {
            return null;
        }
        return VolunteerInfoVo.builder()
                .name(infoDto.getName())
                .sex(infoDto.getSex())
                .birth(infoDto.getBirth())
                .idCard(infoDto.getIdCard())
                .ctime(infoDto.getCtime())
                .mtime(infoDto.getMtime())
                .build();
    }

}
