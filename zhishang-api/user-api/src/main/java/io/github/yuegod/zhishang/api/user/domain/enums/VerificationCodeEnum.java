package io.github.yuegod.zhishang.api.user.domain.enums;

import lombok.Getter;

/**
 * @author quziwei
 * @date 2020/10/01
 * @description 验证码类型
 **/
@Getter
public enum VerificationCodeEnum {
    PHONE(1, "短信验证码"),
    EMAIL(2, "邮箱验证码");

    private Integer type;

    private String desc;

    VerificationCodeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
