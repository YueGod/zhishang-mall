package io.github.yuegod.api.security.enums;

import lombok.Getter;

/**
 * @author quziwei
 * @date 2020/09/30
 * @description 状态
 **/
@Getter
public enum StatusEnum {
    SUCCESS(Byte.valueOf("0"),"成功"),
    FAIL(Byte.valueOf("1"),"失败"),
    WAIT(Byte.valueOf("2"),"等待");

    private Byte status;

    private String desc;

    StatusEnum(Byte status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
