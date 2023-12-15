package com.example.filmotheque.security;

import org.springframework.security.core.GrantedAuthority;
import com.example.filmotheque.bo.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


//cette classe sert à authentifier le user
public class MembreUserDetail implements UserDetails {

    private User user;

    public MembreUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> permissions = new ArrayList<>();
        permissions.add(() -> "ROLE_USER");

        if (this.user.getAdmin()){
           //System.out.println(getAuthorities().toString());
            permissions.add(() -> "ROLE_ADMIN");

        }
        return permissions;
    }

    @Override
    public String getPassword() {
        return user.getMotDePasse();
    }

    @Override
    public String getUsername() {
        return user.getPseudo();
    }

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
