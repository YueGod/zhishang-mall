package io.github.yuegod.api.security.domain.model;

import io.github.yuegod.zhishang.basecode.api.base.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @author quziwei
 * @date 2020/09/30
 * @description
 **/
@Data
public class User extends BaseModel<Integer> {

    private Integer id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 当前用户状态
     */
    private Byte status;

    /**
     * 用户创建时间
     */
    private Date createTime;
}
