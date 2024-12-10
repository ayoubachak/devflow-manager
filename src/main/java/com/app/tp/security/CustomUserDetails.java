package com.app.tp.security;

import com.app.tp.model.Developer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private Developer developer;

    public CustomUserDetails(Developer developer) {
        this.developer = developer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return developer.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return developer.getPassword();
    }

    @Override
    public String getUsername() {
        return developer.getLogin();
    }

    // Implement other methods as needed
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

    // Getter for Developer entity if needed
    public Developer getDeveloper() {
        return developer;
    }
}
