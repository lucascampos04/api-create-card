package com.cartao.validacaodecartao.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ValidacaoExcpetion.class)
    public ResponseEntity<String> handleValidationException(ValidacaoExcpetion ex){
        return ResponseEntity.badRequest().body("Erro de validação : " + ex.getMessage());
    }
}
