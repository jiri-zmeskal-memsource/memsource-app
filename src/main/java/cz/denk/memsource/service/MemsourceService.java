package cz.denk.memsource.service;

import cz.denk.memsource.common.MemsourceRestTemplate;
import cz.denk.memsource.common.dto.LoginRequestDto;
import cz.denk.memsource.common.dto.LoginResponseDto;
import cz.denk.memsource.repository.CredentialsRepository;
import cz.denk.memsource.repository.entity.MemsourceCredentials;
import cz.denk.memsource.web.dto.CredentialsDto;
import cz.denk.memsource.web.dto.CredentialsUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class MemsourceService {

    private final MemsourceRestTemplate memsourceRestTemplate;
    private final CredentialsRepository credentialsRepository;

    public MemsourceService(CredentialsRepository credentialsRepository, MemsourceRestTemplate memsourceRestTemplate) {
        this.memsourceRestTemplate = memsourceRestTemplate;
        this.credentialsRepository = credentialsRepository;
    }


    public void createOrUpdateCredentials(CredentialsUpdateDto credentialsDto) {
        LoginRequestDto loginRequest = new LoginRequestDto(credentialsDto.getUsername(), credentialsDto.getPassword());
        ResponseEntity<LoginResponseDto> loginResponse = memsourceRestTemplate.post("auth/login", loginRequest, LoginResponseDto.class);

        Iterator<MemsourceCredentials> credentialsIterator = this.credentialsRepository.findAll().iterator();

        MemsourceCredentials credentials = convert(credentialsDto, loginResponse.getBody().getToken());
        credentials.setId(credentialsIterator.hasNext() ? credentialsIterator.next().getId() : null);

        credentialsRepository.save(credentials);
    }

    public CredentialsDto getCredentials() {
        Iterator<MemsourceCredentials> credentialsIterator = this.credentialsRepository.findAll().iterator();
        if (credentialsIterator.hasNext()) {
            return convert(credentialsIterator.next());
        } else {
            throw new CredentialsNotFoundException();
        }
    }

    private MemsourceCredentials convert(CredentialsUpdateDto credentialsCreateDto, String token) {
        return new MemsourceCredentials(credentialsCreateDto.getUsername(), credentialsCreateDto.getPassword(), token);
    }

    private CredentialsDto convert(MemsourceCredentials memsourceCredentials) {
        return new CredentialsDto(memsourceCredentials.getUsername());
    }
}
