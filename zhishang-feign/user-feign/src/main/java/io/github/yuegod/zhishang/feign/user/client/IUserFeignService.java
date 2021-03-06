package io.github.yuegod.zhishang.feign.user.client;

import io.github.yuegod.zhishang.api.user.domain.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author quziwei
 * @date 2020/10/01
 * @description
 **/
@FeignClient("userService")
public interface IUserFeignService {

    @GetMapping("/feign/user/{id}")
    User findById(@PathVariable Integer id);


}
