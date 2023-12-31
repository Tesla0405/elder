package com.qxy.elder.portal.enums;

import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum ResponseEnum {

    ERROR(-1,"服务端错误"),

    SUCCESS(0,"成功"),

    PASSWORD_ERROR(1,"密码错误"),

    USERNAME_EXIST(2,"用户名已存在"),

    PARAM_ERROR(3,"参数错误"),

    EMAIL_EXIST(4,"邮箱已存在"),

    NEED_LOGIN(10,"用户未登录，请先登录"),

    USERNAME_OR_PASSWORD_ERROR(11,"用户名或者密码错误"),

    ;

    private Integer code;
    private String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResponseEnum getByCode(Integer code) {
        List<ResponseEnum> enums = Arrays.stream(ResponseEnum.values()).filter(responseEnum -> {
            return responseEnum.getCode().equals(code);
        }).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(enums)) {
            return ERROR;
        }
        return enums.get(0);
    }
}
