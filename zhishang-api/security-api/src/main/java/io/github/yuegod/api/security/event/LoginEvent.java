package io.github.yuegod.api.security.event;

import io.github.yuegod.api.security.domain.model.Login;
import org.springframework.context.ApplicationEvent;

/**
 * @author 屈子威
 * @date 2020/8/30 2:42
 * @description
 */
public class LoginEvent extends ApplicationEvent {
    public LoginEvent(Login source) {
        super(source);
    }

    @Override
    public Login getSource() {
        return (Login) super.getSource();
    }
}
