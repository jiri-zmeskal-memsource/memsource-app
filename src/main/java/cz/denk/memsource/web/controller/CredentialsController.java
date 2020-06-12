package cz.denk.memsource.web.controller;

import cz.denk.memsource.web.dto.CredentialsDto;
import cz.denk.memsource.web.dto.CredentialsUpdateDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CredentialsController {

    private static final String CREDENTIALS_URI = "/api/credentials";

    @PutMapping(path = CREDENTIALS_URI)
    public void updateCredentials(@RequestBody CredentialsUpdateDto credentialsDto) {
    }

    @GetMapping(CREDENTIALS_URI)
    public CredentialsDto getCredentials() {
        return new CredentialsDto("test@memsource.com");
    }
}
