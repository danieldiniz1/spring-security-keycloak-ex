package br.com.train.springkeycloack.config.handler;

import br.com.train.springkeycloack.domain.dto.ExceptionResponseDTO;
import br.com.train.springkeycloack.exception.LoginServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.TimeZone;

@RestControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler(LoginServiceException.class)
    public ResponseEntity<ExceptionResponseDTO> handleInvalidJWTAuthenticationException(LoginServiceException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponseDTO(LocalDateTime.now(TimeZone.getTimeZone("America/Sao_Paulo").toZoneId()),
                        HttpStatus.FORBIDDEN.toString(), e.getMessage()));
    }
}
