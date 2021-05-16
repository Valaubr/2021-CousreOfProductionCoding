package ru.valaubr.services.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.valaubr.models.ServiceUser;

import java.util.Collection;
import java.util.Collections;

public class ServiceUserDetails implements UserDetails {

    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static ServiceUserDetails fromUserEntityToCustomUserDetails(ServiceUser serviceUser) {
        ServiceUserDetails serviceUserDetails = new ServiceUserDetails();
        serviceUserDetails.email = serviceUser.getEmail();
        serviceUserDetails.password = serviceUser.getPassword();
        serviceUserDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(serviceUser.getRole().toString()));
        return serviceUserDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
