package io.github.yuegod.zhishang.component.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.yuegod.zhishang.basecode.service.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author 屈子威
 * @date 2020/8/30 2:11
 * @description 用户实体类
 */
@Getter
@Setter
@ToString
@TableName("t_user")
public class SysUser extends BaseEntity<Integer, SysUser> {
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
