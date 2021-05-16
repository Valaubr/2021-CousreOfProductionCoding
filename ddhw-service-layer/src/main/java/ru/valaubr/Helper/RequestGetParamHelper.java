package ru.valaubr.Helper;

import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import ru.valaubr.dto.UserDto;

public class RequestGetParamHelper {
    private static Gson gson = new Gson();

    public static String getAuthToken(HttpHeaders headers){
        return headers.getFirst("Authorization");
    }

    public static UserDto toUserDto(String userInfo) {
        return gson.fromJson(userInfo, UserDto.class);
    }
}
