package io.github.yuegod.api.security.domain.model;

import io.github.yuegod.zhishang.basecode.api.base.BaseModel;
import lombok.Data;

/**
 * @author 屈子威
 * @date 2020/8/30 19:27
 * @description 权限
 */
@Data
public class Permission extends BaseModel<Integer> {
    private String permission;
}
