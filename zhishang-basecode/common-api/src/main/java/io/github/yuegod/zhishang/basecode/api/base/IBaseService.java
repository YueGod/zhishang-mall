package io.github.yuegod.zhishang.basecode.api.base;


import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<M extends BaseModel<ID>,ID extends Serializable,E> extends IService<E> {
    int save(M m);

    int add(M m);

    int update(M m);

    M findById(ID id);

    M findOne(M m);

    int deleteById(ID id);

    int deleteOne(M m);

    List<M> findAll();

    List<M> findAll(M m);

    PageResp<M> findAll(PageReq pageReq);

    PageResp<M> findAll(M m, PageReq pageReq);

}
