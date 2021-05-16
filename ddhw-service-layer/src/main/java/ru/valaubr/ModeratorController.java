package ru.valaubr;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.valaubr.Helper.RequestGetParamHelper;
import ru.valaubr.dto.AcceptorDto;
import ru.valaubr.dto.ModerationQueueDto;
import ru.valaubr.services.ModerationService;
import ru.valaubr.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/moderation")
public class ModeratorController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModerationService moderationService;
    private String auth;
    private final Gson gson = new Gson();

    @GetMapping
    public List<ModerationQueueDto> getAllModerationDocs(@RequestHeader HttpHeaders headers) {
        auth = RequestGetParamHelper.getAuthToken(headers);
        return moderationService.getAllDocToModeration(auth);
    }

    @PostMapping("/accept")
    public ResponseEntity accept(@RequestHeader HttpHeaders headers, @RequestBody String data) {
        AcceptorDto input = gson.fromJson(data, AcceptorDto.class);
        auth = RequestGetParamHelper.getAuthToken(headers);
        return moderationService.accept(auth, input);
    }

    @PostMapping("/reject")
    public ResponseEntity reject(@RequestHeader HttpHeaders headers, @RequestBody String data) {
        AcceptorDto input = gson.fromJson(data, AcceptorDto.class);
        auth = RequestGetParamHelper.getAuthToken(headers);
        return moderationService.reject(auth, input);
    }
}
