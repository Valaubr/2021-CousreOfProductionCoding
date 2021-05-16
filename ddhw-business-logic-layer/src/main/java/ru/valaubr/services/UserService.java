package ru.valaubr.services;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.valaubr.dto.UserDto;
import ru.valaubr.enums.Role;
import ru.valaubr.jpa.ServiceUserRepo;
import ru.valaubr.models.ServiceUser;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ServiceUserRepo repo;

    public ServiceUser saveUser(@NotNull ServiceUser serviceUser) {
        if (serviceUser.getRole() == null) {
            serviceUser.setRole(Role.ROLE_USER);
        }
        serviceUser.setPassword(passwordEncoder.encode(serviceUser.getPassword()));
        return repo.save(serviceUser);
    }

    public ServiceUser findByEmail(@NotNull String email) {
        return repo.findByEmail(email);
    }

    public ServiceUser findByEmailAndPassword(@NotNull String email, @NotNull String password) {
        ServiceUser userEntity = findByEmail(email);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    public ResponseEntity createUser(@NotNull String password,
                                     @NotNull String fName,
                                     @NotNull String lName,
                                     @NotNull String email) {
        ServiceUser serviceUser = new ServiceUser();
        serviceUser.setPassword(password);
        serviceUser.setFName(fName);
        serviceUser.setLName(lName);
        serviceUser.setEmail(email);
        try {
            saveUser(serviceUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public List<UserDto> getAllUsers() {
        List<ServiceUser> users = repo.findAll();
        List<UserDto> usersDto = new ArrayList<>();
        users.forEach(serviceUser -> usersDto.add(new UserDto(java.util.Optional.ofNullable(serviceUser))));
        return usersDto;
    }

    public ResponseEntity updateUserRole(UserDto userDto) {
        if (userDto.getRole() != null && userDto.getEmail() != null) {
            ServiceUser serviceUser = findByEmail(userDto.getEmail());
            serviceUser.setRole(Role.valueOf(userDto.getRole()));
            saveUser(serviceUser);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
