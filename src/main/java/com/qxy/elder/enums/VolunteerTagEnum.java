package com.qxy.elder.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum VolunteerTagEnum {
    COOK(0, "烹饪"),

    TRANSFER(1, "跑腿"),

    ACCOMPANY(2, "陪伴"),

    TRAVEL(3, "出行"),

    NURSE(4, "护理"),
    ;

    @Getter
    private final Integer code;
    @Getter
    private final String value;

    public static VolunteerTagEnum getByCode(Integer code) {
        for (VolunteerTagEnum volunteerRoleEnum : VolunteerTagEnum.values()) {
            if (volunteerRoleEnum.getCode().equals(code)) {
                return volunteerRoleEnum;
            }
        }
        throw new IllegalArgumentException("unknown volunteerRoleEnum code:" + code);
    }

    public static VolunteerTagEnum getByValue(String value) {
        for (VolunteerTagEnum volunteerRoleEnum : VolunteerTagEnum.values()) {
            if (volunteerRoleEnum.getValue().equals(value)) {
                return volunteerRoleEnum;
            }
        }
        throw new IllegalArgumentException("unknown volunteerRoleEnum value:" + value);
    }
}
