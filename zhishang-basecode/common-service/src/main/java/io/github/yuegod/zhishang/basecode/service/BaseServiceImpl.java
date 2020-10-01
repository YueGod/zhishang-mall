package io.github.yuegod.zhishang.basecode.service;


import io.github.yuegod.zhishang.basecode.api.base.BaseModel;
import io.github.yuegod.zhishang.basecode.api.base.IBaseService;
import io.github.yuegod.zhishang.basecode.api.base.PageReq;
import io.github.yuegod.zhishang.basecode.api.base.PageResp;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 屈子威
 */
@Slf4j
public abstract class BaseServiceImpl<E, M extends BaseModel<ID>, ID extends Serializable,D extends BaseMapper<E>> extends ServiceImpl<D,E> implements IBaseService<M, ID, E> {

    protected abstract BaseMapper getDao();

    protected abstract E model2Entity(M m);

    protected abstract M entity2Model(E e);

    protected List<M> entity2Model(List<E> list) {
        List<M> mList = new ArrayList<>();
        list.forEach(e -> {
            mList.add(entity2Model(e));
        });
        return mList;
    }

    private PageResp<M> page2PageResp(IPage<E> page) {
        PageResp<M> pageResp = new PageResp<>();
        pageResp.setTotalRow(page.getTotal());
        pageResp.setTotalPage(page.getPages());
        pageResp.setPageNumber(page.getCurrent());
        pageResp.setPageSize(page.getSize());
        pageResp.setList(entity2Model(page.getRecords()));
        return pageResp;
    }

    ;


    @Override
    public int add(M m) {
        return getDao().insert(model2Entity(m));
    }

    @Override
    public int update(M m) {
        return getDao().insert(model2Entity(m));
    }

    @Override
    public M findById(ID id) {
        E e = (E) getDao().selectById(id);
        return entity2Model(e);
    }

    @Override
    public M findOne(M m) {
        QueryWrapper<E> wrapper = new QueryWrapper<>();
        wrapper.setEntity(model2Entity(m));
        return entity2Model((E) getDao().selectOne(wrapper));
    }

    @Override
    public int deleteById(ID id) {
        return getDao().deleteById(id);
    }

    @Override
    public int deleteOne(M m) {
        QueryWrapper<E> wrapper = new QueryWrapper<>();
        wrapper.setEntity(model2Entity(m));
        return getDao().delete(wrapper);
    }

    @Override
    public List<M> findAll() {
        QueryWrapper<E> wrapper = new QueryWrapper<>();
        return getDao().selectList(wrapper);
    }

    @Override
    public List<M> findAll(M m) {
        QueryWrapper<E> wrapper = new QueryWrapper<>();
        wrapper.setEntity(model2Entity(m));
        return getDao().selectList(wrapper);
    }

    @Override
    public PageResp<M> findAll(PageReq pageReq) {
        QueryWrapper<E> wrapper = new QueryWrapper<>();
        Page<E> page = new Page<>(pageReq.getPageNumber(), pageReq.getPageSize());
        IPage iPage = getDao().selectPage(page, wrapper);
        return page2PageResp(iPage);
    }

    @Override
    public PageResp<M> findAll(M m, PageReq pageReq) {
        QueryWrapper<E> wrapper = new QueryWrapper<>();
        wrapper.setEntity(model2Entity(m));
        Page<E> page = new Page<>(pageReq.getPageNumber(), pageReq.getPageSize());
        IPage iPage = getDao().selectPage(page, wrapper);
        return page2PageResp(iPage);
    }

    protected boolean isNotEmpty(Object o){
        if (!"".equals(o) && o != null){
            return true;
        }else {
            return false;
        }
    }
}
