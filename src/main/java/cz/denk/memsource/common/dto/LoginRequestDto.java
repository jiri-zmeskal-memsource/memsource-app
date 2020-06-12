package cz.denk.memsource.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NONE;

@JsonTypeInfo(use = NONE)
public class LoginRequestDto {

    private final String userName;
    private final String password;

    @JsonCreator
    public LoginRequestDto(@JsonProperty("userName") String username, @JsonProperty("password") String password) {
        this.userName = username;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
