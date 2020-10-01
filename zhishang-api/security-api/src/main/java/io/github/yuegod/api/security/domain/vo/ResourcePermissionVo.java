package io.github.yuegod.api.security.domain.vo;

import lombok.Data;

/**
 * @author 屈子威
 * @date 2020/8/31 6:19
 * @description 资源和权限
 */
@Data
public class ResourcePermissionVo {

    private String resource;

    private String permission;
}
