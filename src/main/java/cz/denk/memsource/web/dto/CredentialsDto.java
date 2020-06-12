package cz.denk.memsource.web.dto;

public class CredentialsDto {

    private String username;

    public CredentialsDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
