package io.github.yuegod.zhishang.component.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.yuegod.zhishang.basecode.service.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 屈子威
 * @date 2020/8/30 19:26
 * @description 权限表
 */
@Getter
@Setter
@ToString
@TableName("t_permission")
public class SysPermission extends BaseEntity<Integer,SysPermission> {
    private String permission;
}
