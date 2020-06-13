package cz.denk.memsource.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class CredentialsForAuthenticationMissingException extends RuntimeException{
}
