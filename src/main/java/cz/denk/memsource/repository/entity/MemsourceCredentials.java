package cz.denk.memsource.repository.entity;

import org.springframework.data.annotation.Id;

public class MemsourceCredentials {

    @Id
    private Long id;
    private final String username;
    private final String password;
    private final String token;

    public MemsourceCredentials(String username, String password, String token) {
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
