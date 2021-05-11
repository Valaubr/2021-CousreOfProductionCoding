package ru.valaubr.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.valaubr.jpa.ServiceUserRepo;
import ru.valaubr.models.ServiceUser;

import java.util.stream.Collectors;

public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private ServiceUserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ServiceUser user = repo.findByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return new User(user.getEmail(), user.getPassword(), user.getRole().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().toString()))
                .collect(Collectors.toList()));
    }
}