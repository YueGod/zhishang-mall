package io.github.yuegod.zhishang.component.security.configuration;

import io.github.yuegod.api.security.domain.vo.ResourcePermissionVo;
import io.github.yuegod.api.security.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 资源权限初始化与获取
 */
@Service
public class SystemInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IResourceService resourceService;

    private static Map<String[], ArrayList<ConfigAttribute>> resourceMap;

//    @PostConstruct
//    public void init(){
//        this.resourceService = SpringUtil.getBean(ResourceService.class);
//    }

    /**
     * 初始化资源权限
     */
    @PostConstruct
    private void loadResourceDefine() {
        resourceMap = new HashMap<>();
        List<ResourcePermissionVo> resourcePermissions = resourceService.findResourcePermissions();
        if (!ObjectUtils.isEmpty(resourcePermissions)){
            for (ResourcePermissionVo resourcePermissionVo :resourcePermissions) {
                String[] split = resourcePermissionVo.getResource().split(":");
                if (resourceMap.containsKey(split)){
                    List<ConfigAttribute> attributes = resourceMap.get(split);
                    attributes.add(new SecurityConfig(resourcePermissionVo.getPermission()));
                }else{
                    ArrayList<ConfigAttribute> configAttributes = new ArrayList<>();
                    configAttributes.add(new SecurityConfig(resourcePermissionVo.getPermission()));
                    resourceMap.put(split,configAttributes);
                }
            }
        }

    }

    /**
     * 匹配资源权限
     *
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) o;
        if (CollectionUtils.isEmpty(resourceMap)) {
            loadResourceDefine();
        }
        for (String[] split : resourceMap.keySet()) {
            RequestMatcher requestMatcher;
            if (StringUtils.isEmpty(split[0]) || "ALL".equalsIgnoreCase(split[0])) {
                requestMatcher = new AntPathRequestMatcher(split[1]);
            } else {
                requestMatcher = new AntPathRequestMatcher(split[1], split[0]);
            }
            if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(split);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<>();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
