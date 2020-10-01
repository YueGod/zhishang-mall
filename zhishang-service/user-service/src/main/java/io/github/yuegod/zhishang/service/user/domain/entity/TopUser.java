package io.github.yuegod.zhishang.service.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.yuegod.zhishang.basecode.service.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author quziwei
 * @date 2020/10/01
 * @description 商城用户
 **/
@TableName("top_user")
@Getter
@Setter
@ToString
public class TopUser extends BaseEntity<Integer, TopUser> {

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

}
