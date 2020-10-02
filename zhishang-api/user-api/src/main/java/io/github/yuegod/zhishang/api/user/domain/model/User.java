package io.github.yuegod.zhishang.api.user.domain.model;

import io.github.yuegod.zhishang.basecode.api.annotation.FeignBean;
import io.github.yuegod.zhishang.basecode.api.base.BaseModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author quziwei
 * @date 2020/10/01
 * @description
 **/
@Data
@FeignBean
public class User extends BaseModel<Integer> {
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 生日
     */
    private Date birthDay;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String profilePhoto;

    /**
     * 总消费金额
     */
    private BigDecimal totalAmount;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * userId
     */
    private Integer userId;

    private String username;

    private String password;
}
