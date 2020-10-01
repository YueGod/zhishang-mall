package io.github.yuegod.zhishang.component.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.yuegod.zhishang.basecode.service.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author 屈子威
 * @Date 2020/9/1 11:27
 * @description 角色表
 */
@Getter
@Setter
@ToString
@TableName("t_role")
public class SysRole extends BaseEntity<Integer,SysRole> {
    private String name;
}
