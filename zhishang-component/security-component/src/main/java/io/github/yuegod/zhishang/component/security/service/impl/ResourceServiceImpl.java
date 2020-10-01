package io.github.yuegod.zhishang.component.security.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.yuegod.api.security.domain.model.Resource;
import io.github.yuegod.api.security.domain.vo.ResourcePermissionVo;
import io.github.yuegod.api.security.service.IResourceService;
import io.github.yuegod.zhishang.basecode.service.BaseServiceImpl;
import io.github.yuegod.zhishang.component.security.dao.ResourceMapper;
import io.github.yuegod.zhishang.component.security.entity.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author 屈子威
 * @date 2020/8/31 6:34
 * @description 资源
 */
@Service
@Primary
public class ResourceServiceImpl extends BaseServiceImpl<SysResource, Resource, Integer, ResourceMapper> implements IResourceService<SysResource> {
    @Autowired
    private ResourceMapper dao;


    @Override
    public List<ResourcePermissionVo> findResourcePermissions() {
        return null;
    }

    @Override
    protected BaseMapper getDao() {
        return dao;
    }

    @Override
    protected SysResource model2Entity(Resource resource) {
        SysResource sysResource = null;
        if (!ObjectUtils.isEmpty(resource)) {
            sysResource = new SysResource();
            sysResource.setResource(resource.getResource());
            sysResource.setId(resource.getId());
        }
        return sysResource;
    }

    @Override
    protected Resource entity2Model(SysResource sysResource) {
        Resource resource = null;
        if (!ObjectUtils.isEmpty(sysResource)) {
            resource = new Resource();
            resource.setResource(sysResource.getResource());
            resource.setId(sysResource.getId());
        }
        return resource;
    }

    @Override
    public int save(Resource resource) {
        if (resource.isCreate()) {
            return add(resource);
        } else {
            SysResource sysResource = dao.selectById(resource.getId());
            if (isNotEmpty(resource.getResource())) {
                sysResource.setResource(resource.getResource());
            }
            return dao.updateById(sysResource);
        }
    }
}
