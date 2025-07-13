package br.com.train.springkeycloack.domain.dto;

import java.time.LocalDateTime;

public record ExceptionResponseDTO(LocalDateTime timestamp, String errorCode, String details) {
}
