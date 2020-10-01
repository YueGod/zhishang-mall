package io.github.yuegod.zhishang.basecode.api.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@ApiModel
public class PageResp<M> extends PageReq {
    public PageResp(){

    }

    public PageResp(PageResp pageResp){
        setTotalPage(pageResp.getTotalPage());
        setTotalRow(pageResp.getTotalRow());
        setPageNumber(pageResp.getPageNumber());
        setPageSize(pageResp.getPageSize());
        setList((List<M>) new ArrayList<>());

    }

    @ApiModelProperty("总数量")
    private Long totalRow;
    @ApiModelProperty("总页数")
    private Long totalPage;
    @ApiModelProperty("返回的数据")
    private List<M> list;

}
