package br.com.train.springkeycloack.controller;

import br.com.train.springkeycloack.domain.dto.TokenResponseDTO;
import br.com.train.springkeycloack.domain.form.UserForm;
import br.com.train.springkeycloack.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final LoginService<TokenResponseDTO> loginService;

    public AuthController(LoginService<TokenResponseDTO> loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> doLogin(@Valid @RequestBody UserForm userForm) {
        TokenResponseDTO tokenResponseDTO = loginService.doLogin(userForm);
        if (Objects.nonNull(tokenResponseDTO)) {
            return ResponseEntity.status(200).body(tokenResponseDTO);
        }
        return ResponseEntity.status(500).body("Unexpected error during login");
    }
}
