package io.github.yuegod.api.security.domain.model;

import io.github.yuegod.zhishang.basecode.api.base.BaseModel;
import lombok.Data;

/**
 * @author 屈子威
 * @date 2020/8/31 6:17
 * @description 资源
 */
@Data
public class Resource extends BaseModel<Integer> {
    private String resource;
}
