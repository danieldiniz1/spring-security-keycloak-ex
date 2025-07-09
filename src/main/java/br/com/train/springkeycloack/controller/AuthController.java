package br.com.train.springkeycloack.controller;

import br.com.train.springkeycloack.domain.form.UserForm;
import br.com.train.springkeycloack.utils.HttpParamsMapBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

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

    public AuthController(HttpHeaders headers, RestTemplate restTemplate) {
        this.headers = headers;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@Valid @RequestBody UserForm userForm) {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = HttpParamsMapBuilder.builder()
                .withUsername(userForm.username())
                .withPassword(userForm.password())
                .withClientId(keycloakClientId)
                .withClientSecret(keycloakClientSecret)
                .withGrantType(keycloakGrantType)
                .build();

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    keycloakServerUrl + "/realms/" + keycloakRealm + "/protocol/openid-connect/token",
                    requestEntity,
                    String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(response.getBody());
            }
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body("Error during login: " + e.getMessage());
        }
        return ResponseEntity.status(500).body("Unexpected error during login");
    }
}
