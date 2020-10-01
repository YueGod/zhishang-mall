package io.github.yuegod.zhishang.component.security.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author 屈子威
 * @date 2020/8/30 19:40
 * @description 用户信息
 */
@Data
@AllArgsConstructor
public class UserInfo implements UserDetails {
    private Integer userId;

    private String username;

    private String password;

    private List<SimpleGrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
