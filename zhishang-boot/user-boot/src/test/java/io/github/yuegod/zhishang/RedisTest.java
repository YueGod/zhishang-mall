package io.github.yuegod.zhishang;

import io.UserWebApplication;
import io.github.yuegod.zhishang.api.user.constant.UserRedisConstants;
import io.github.yuegod.zhishang.api.user.domain.model.User;
import io.github.yuegod.zhishang.api.user.service.ITopUserService;
import io.github.yuegod.zhishang.component.security.dao.UserMapper;
import io.github.yuegod.zhishang.service.user.dao.TopUserMapper;
import io.github.yuegod.zhishang.service.user.domain.entity.TopUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author quziwei
 * @date 2020/10/03
 * @description
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ITopUserService userService;

    @Test
    public void save() {
        User user = userService.findByUserId(1);
        redisTemplate.opsForHash().put(UserRedisConstants.USER_INFO, "1", user);
    }

    @Test
    public void get(){
        System.out.println(redisTemplate.opsForHash().get(UserRedisConstants.USER_INFO,"1"));
    }

}
