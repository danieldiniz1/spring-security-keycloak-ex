package br.com.train.springkeycloack.service.impl;

import br.com.train.springkeycloack.domain.dto.TokenResponseDTO;
import br.com.train.springkeycloack.domain.form.UserForm;
import br.com.train.springkeycloack.exception.LoginServiceException;
import br.com.train.springkeycloack.service.LoginService;
import br.com.train.springkeycloack.utils.HttpParamsMapBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class DefaultKeycloakLoginService implements LoginService<TokenResponseDTO> {

    private static final Logger log = LoggerFactory.getLogger(DefaultKeycloakLoginService.class);
    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;
    @Value("${keycloak.realm}")
    private String keycloakRealm;
    @Value("${keycloak.resource}")
    private String keycloakClientId;
    @Value("${keycloak.credentials.secret}")
    private String keycloakClientSecret;
    @Value("${keycloak.grant-type}")
    private String keycloakGrantType;

    private final HttpHeaders headers;
    private final RestTemplate restTemplate;

    public DefaultKeycloakLoginService(HttpHeaders headers, RestTemplate restTemplate) {
        this.headers = headers;
        this.restTemplate = restTemplate;
    }

    @Override
    public TokenResponseDTO doLogin(UserForm userForm) {
        HttpEntity<MultiValueMap<String, String>> requestEntity = prepareMultiValueMapHttpEntity(userForm);

        try {
            ResponseEntity<TokenResponseDTO> response = restTemplate.postForEntity(
                    keycloakServerUrl + "/realms/" + keycloakRealm + "/protocol/openid-connect/token",
                    requestEntity,
                    TokenResponseDTO.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        } catch (HttpClientErrorException e) {
            log.error(e.getResponseBodyAsString());
//            throw new LoginServiceException("Login failed: ");
        }
        throw new LoginServiceException("Login failed: Invalid credentials");
    }

    private HttpEntity<MultiValueMap<String, String>> prepareMultiValueMapHttpEntity(UserForm userForm) {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = HttpParamsMapBuilder.builder()
                .withUsername(userForm.username())
                .withPassword(userForm.password())
                .withClientId(keycloakClientId)
                .withClientSecret(keycloakClientSecret)
                .withGrantType(keycloakGrantType)
                .build();

        return new HttpEntity<>(formData, headers);
    }

    @Override
        public TokenResponseDTO refreshToken (String refreshToken){
            return null;
        }

        @Override
        public TokenResponseDTO doLogout (String refreshToken){
            return null;
        }
    }
