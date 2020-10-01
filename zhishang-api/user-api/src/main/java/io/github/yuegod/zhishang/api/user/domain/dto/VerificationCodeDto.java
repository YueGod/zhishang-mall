package io.github.yuegod.zhishang.api.user.domain.dto;

import lombok.Data;

/**
 * @author quziwei
 * @date 2020/10/01
 * @description
 **/
@Data
public class VerificationCodeDto {

    private String code;

    private Integer type;

}
