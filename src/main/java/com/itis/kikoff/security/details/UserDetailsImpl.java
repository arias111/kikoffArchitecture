package com.itis.kikoff.security.details;

import com.itis.kikoff.models.enums.State;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.itis.kikoff.models.auth.User;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return user.getHashPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    //не просрочен ли аккаунт
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // не заблокирован ли аккаунт
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //логин и пароль не просрочен
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // включен ли пользователь
    @Override
    public boolean isEnabled() {
        return user.isActive();
    }

    public User getUser() {
        return user;
    }
}
