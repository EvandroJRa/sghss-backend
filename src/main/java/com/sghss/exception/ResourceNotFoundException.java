package com.sghss.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Esta anotação faz a mágica: quando esta exceção é lançada por um controller,
// o Spring a converte automaticamente em uma resposta HTTP 404 Not Found.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}