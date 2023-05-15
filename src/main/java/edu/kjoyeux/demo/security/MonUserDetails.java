package edu.kjoyeux.demo.security;

import edu.kjoyeux.demo.model.MyUser;
import edu.kjoyeux.demo.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MonUserDetails implements UserDetails {
    MyUser MyUser;

    public edu.kjoyeux.demo.model.MyUser getMyUser() {
        return MyUser;
    }

    public MonUserDetails(MyUser MyUser){
    this.MyUser=MyUser;


}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*List<GrantedAuthority> role = new ArrayList<>();
        if(MyUser.isAdmin()){
            role.add(new SimpleGrantedAuthority("ROLE_ADMINISTRATEUR"));
        }else{
            role.add(new SimpleGrantedAuthority("ROLE_UTILISATEUR"));
        }
       return role;
        if(MyUser.getRole()!=null){
            return List.of(new SimpleGrantedAuthority(MyUser.getRole().getNom()));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_UTILISATEUR"));*/
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : MyUser.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getNom()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return MyUser.getMotDePasse();
    }

    @Override
    public String getUsername() {
        return MyUser.getEmail();
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
