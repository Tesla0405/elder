package com.qxy.elder.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MissionStatusEnum {

    WAIT_PUBLISH(0, "待发布"),
    WAIT_ACCEPT(1, "待接取"),
    WAIT_COMPLETE(2, "待完成"),
    ACCOMPLISHED(3, "已完成"),
    INVALID(4,"已失效"),
    ;

    @Getter
    private final Integer code;
    @Getter
    private final String desc;

    public static MissionStatusEnum getByCode(Integer code) {
        for (MissionStatusEnum missionStatusEnum : MissionStatusEnum.values()) {
            if (missionStatusEnum.getCode().equals(code)) {
                return missionStatusEnum;
            }
        }
        throw new IllegalArgumentException("unknown MissionStatusEnum code:" + code);
    }
}
