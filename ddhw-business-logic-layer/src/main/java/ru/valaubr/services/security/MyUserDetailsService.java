package ru.valaubr.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.valaubr.jpa.ServiceUserRepo;
import ru.valaubr.models.ServiceUser;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private ServiceUserRepo repo;

    @Override
    public ServiceUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ServiceUser user = repo.findByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return ServiceUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}