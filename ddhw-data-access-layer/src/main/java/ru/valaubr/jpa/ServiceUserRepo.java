package ru.valaubr.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.valaubr.models.ServiceUser;

public interface ServiceUserRepo extends JpaRepository<ServiceUser, String> {
    ServiceUser findByEmail(String email);
}
