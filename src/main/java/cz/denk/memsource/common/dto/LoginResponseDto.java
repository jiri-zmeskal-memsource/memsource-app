package cz.denk.memsource.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NONE;

public class LoginResponseDto {

    private final String token;

    @JsonCreator
    public LoginResponseDto(@JsonProperty("token") String token) {
        this.token = token;
    }


    public String getToken() {
        return token;
    }
}
