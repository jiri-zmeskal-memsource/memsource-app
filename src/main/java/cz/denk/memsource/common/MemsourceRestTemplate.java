package cz.denk.memsource.common;

import cz.denk.memsource.repository.entity.MemsourceCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

@Component
public class MemsourceRestTemplate {

    private static final UriTemplate MEMSOURCE_API_TEMPLATE = new UriTemplate("{baseUrl}/{uri}");
    private static final UriTemplate MEMSOURCE_API_TEMPLATE_AUTH = new UriTemplate("{baseUrl}/{uri}?token={token}");

    private final String baseUrl;
    private final RestTemplate restTemplate;

    public MemsourceRestTemplate(RestTemplate restTemplate, @Value("${memsource.api.baseUrl}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> post(final String uri, Object requestEntity, Class<T> responseType) {
        notEmpty(uri, "uri");
        notNull(requestEntity, "requestEntity");
        notNull(responseType, "responseType");

        final String requestUrl = MEMSOURCE_API_TEMPLATE.expand(baseUrl, uri).toASCIIString();
        return restTemplate.postForEntity(requestUrl, requestEntity, responseType);
    }

    public <T> ResponseEntity<T> get(String uri, Class<T> responseType, MemsourceCredentials credentials) {
        notEmpty(uri, "uri");
        notNull(responseType, "responseType");
        notNull(credentials, "credentials");

        final String requestUrl = MEMSOURCE_API_TEMPLATE_AUTH.expand(baseUrl, uri, credentials.getToken()).toASCIIString();
        return restTemplate.getForEntity(requestUrl, responseType);
    }
}
