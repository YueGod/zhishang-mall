package io.github.yuegod.zhishang.basecode.api.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Setter
@ToString
@ApiModel
public class PageReq implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("每页大小")
    private Long pageSize = 20L;
    @ApiModelProperty("当前页")
    private Long pageNumber = 1L;
}
