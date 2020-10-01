package io.github.yuegod.zhishang.component.security.configuration;

import io.github.yuegod.api.security.domain.model.User;
import io.github.yuegod.api.security.enums.StatusEnum;
import io.github.yuegod.api.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 屈子威
 * @date 2020/8/30 18:27
 * @description
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserService service;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(s);
        user = (User) service.findOne(user);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        if (!user.getStatus().equals(StatusEnum.SUCCESS.getStatus())) {
            throw new DisabledException("账号不可用");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<String> roles = service.findRolesByUserId(user.getId());
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new UserInfo(user.getId(), user.getUsername(), user.getPassword(), authorities);
    }
}
