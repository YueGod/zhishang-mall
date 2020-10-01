package io.github.yuegod.api.security.domain.model;

import io.github.yuegod.zhishang.basecode.api.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 屈子威
 * @Date 2020/9/1 11:24
 * @description 角色
 */
@Data
@ApiModel
public class Role extends BaseModel<Integer> {

    @ApiModelProperty("角色名称")
    private String name;

}
