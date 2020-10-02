package io.github.yuegod.zhishang.component.feign.component;


import io.github.yuegod.zhishang.basecode.api.annotation.FeignBean;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author quziwei
 * @date 2020/10/02
 * @description 注解处理
 **/
@Component
public class FeignBeanComponent {
    public ConcurrentHashMap<String, Class> feignBeanCache = new ConcurrentHashMap();

    public FeignBeanComponent() {
        Set<Class<?>> classSet = findClass("io.github.yuegod.zhishang.api", FeignBean.class);
        classSet.forEach(c -> {
            feignBeanCache.put(c.getName(), c);
        });
    }

    /**
     * 通过路径和注解扫描到相应的类，并加入到beanContainer集合中
     *
     * @return 返回扫描到的相应注解的Class集合
     */
    public static Set<Class<?>> findClass(String path, Class<? extends Annotation> clazz) {
        Reflections f = new Reflections(path);
        Set<Class<?>> set = f.getTypesAnnotatedWith(clazz);
        for (Class<?> c : set) {
            //判断要注入的类不是抽象类和接口
            if (!Modifier.isAbstract(c.getModifiers()) && !Modifier.isInterface(c.getModifiers())) {
                set.add(c);
            } else {
                continue;
            }
        }
        return set;
    }
}
