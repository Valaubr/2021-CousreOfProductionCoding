package ru.valaubr.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.valaubr.models.ServiceUser;

@Component
public interface ServiceUserRepo extends JpaRepository<ServiceUser, Long> {
    ServiceUser findByEmail(String token);
}
