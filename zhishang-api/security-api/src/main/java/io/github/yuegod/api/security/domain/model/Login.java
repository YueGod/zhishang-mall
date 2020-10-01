package io.github.yuegod.api.security.domain.model;

import lombok.Data;

import java.util.Date;

/**
 * @author 屈子威
 * @date 2020/8/30 2:44
 * @description
 */
@Data
public class Login {

    private String username;

    private String ip;

    private Date loginTime;

}
