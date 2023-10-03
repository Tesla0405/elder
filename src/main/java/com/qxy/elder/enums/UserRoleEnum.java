package com.qxy.elder.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserRoleEnum {

    VOLUNTEER(0, "志愿者"),
    ELDER(1, "老人"),
    ;

    @Getter
    private final Integer code;
    @Getter
    private final String desc;

    public static UserRoleEnum getByCode(Integer code) {
        for (UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
            if (userRoleEnum.getCode().equals(code)) {
                return userRoleEnum;
            }
        }
        throw new IllegalArgumentException("unknown userRoleEnum code:" + code);
    }
}
