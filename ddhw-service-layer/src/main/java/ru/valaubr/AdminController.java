package ru.valaubr;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.valaubr.Helper.RequestGetParamHelper;
import ru.valaubr.dto.CatalogWhiteListDto;
import ru.valaubr.dto.UserDto;
import ru.valaubr.models.ServiceUser;
import ru.valaubr.services.PermissionService;
import ru.valaubr.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    private Gson gson = new Gson();

    @GetMapping("/users")
    public List<UserDto> getAllUser() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody String userInfo) {
        ServiceUser serviceUser = gson.fromJson(userInfo, ServiceUser.class);
        return userService.createUser(serviceUser.getPassword(), serviceUser.getFName(), serviceUser.getLName(), serviceUser.getEmail());
    }

    @PutMapping("/users")
    public ResponseEntity changeUserRole(@RequestBody String role, @RequestHeader HttpHeaders headers) {
        UserDto userDto = gson.fromJson(role, UserDto.class);
        return userService.updateUserRole(userDto);
    }

    @PostMapping("/mod")
    public void setModerator(@RequestBody String data, @RequestHeader HttpHeaders headers) {
        CatalogWhiteListDto catalogWhiteListDto = gson.fromJson(data, CatalogWhiteListDto.class);
        permissionService.setCatalogPermToUser(catalogWhiteListDto);
    }
}
