package io.github.yuegod.zhishang.service.order.service.impl;

import io.github.yuegod.zhishang.api.user.domain.model.User;
import io.github.yuegod.zhishang.feign.user.client.IUserFeignService;
import org.springframework.stereotype.Service;

/**
 * @author quziwei
 * @date 2020/10/01
 * @description
 **/
@Service
public class OrderService {
    private final IUserFeignService userFeignService;
    public OrderService(IUserFeignService userFeignService) {
        this.userFeignService = userFeignService;
    }

    public User findById(Integer id){
        return userFeignService.findById(id);
    }

}
