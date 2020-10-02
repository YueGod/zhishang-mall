package io.github.yuegod.zhishang.web.order.controller;

import io.github.yuegod.zhishang.api.user.domain.model.User;
import io.github.yuegod.zhishang.service.order.service.impl.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author quziwei
 * @date 2020/10/01
 * @description
 **/
@RestController
@RequestMapping("/top/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        User user = orderService.findById(id);
        log.info("{}", user);
        return user;
    }

}
