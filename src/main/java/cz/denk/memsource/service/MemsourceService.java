package cz.denk.memsource.service;

import cz.denk.memsource.common.MemsourceRestTemplate;
import cz.denk.memsource.common.dto.LoginRequestDto;
import cz.denk.memsource.common.dto.LoginResponseDto;
import cz.denk.memsource.common.dto.ProjectsPageResponseDto;
import cz.denk.memsource.repository.CredentialsRepository;
import cz.denk.memsource.repository.entity.MemsourceCredentials;
import cz.denk.memsource.web.dto.CredentialsDto;
import cz.denk.memsource.web.dto.CredentialsUpdateDto;
import cz.denk.memsource.web.dto.ProjectDto;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<MemsourceCredentials> memsourceCredentialsOptional = loadMemsourceCredentialsFromRepository();

        if (memsourceCredentialsOptional.isPresent()) {
            return convert(memsourceCredentialsOptional.get());
        } else {
            throw new CredentialsNotFoundException();
        }
    }

    public void removeCredentials() {
        credentialsRepository.deleteAll();
    }

    public List<ProjectDto> listProjects() {
        Optional<MemsourceCredentials> memsourceCredentialsOptional = loadMemsourceCredentialsFromRepository();

        if (!memsourceCredentialsOptional.isPresent()) {
            throw new CredentialsForAuthenticationMissingException();
        }

        return memsourceRestTemplate.get("projects", ProjectsPageResponseDto.class, memsourceCredentialsOptional.get()).getBody().getContent().stream()
                .map(p -> new ProjectDto(p.getName(), p.getStatus(), p.getSourceLang(), p.getTargetLangs())).collect(Collectors.toList());
    }

    @Scheduled(fixedRate = 23 * 60 * 60 * 1000)
    public void refreshMemsourceToken() {
        Optional<MemsourceCredentials> memsourceCredentialsOptional = loadMemsourceCredentialsFromRepository();
        if (!memsourceCredentialsOptional.isPresent()) {
            return;
        }

        MemsourceCredentials memsourceCredentials = memsourceCredentialsOptional.get();
        this.createOrUpdateCredentials(new CredentialsUpdateDto(memsourceCredentials.getUsername(), memsourceCredentials.getPassword()));
    }

    private Optional<MemsourceCredentials> loadMemsourceCredentialsFromRepository() {
        Iterator<MemsourceCredentials> credentialsIterator = this.credentialsRepository.findAll().iterator();
        if (credentialsIterator.hasNext()) {
            return Optional.of(credentialsIterator.next());
        } else {
            return Optional.empty();
        }
    }

    private MemsourceCredentials convert(CredentialsUpdateDto credentialsCreateDto, String token) {
        return new MemsourceCredentials(credentialsCreateDto.getUsername(), credentialsCreateDto.getPassword(), token);
    }

    private CredentialsDto convert(MemsourceCredentials memsourceCredentials) {
        return new CredentialsDto(memsourceCredentials.getUsername());
    }
}
