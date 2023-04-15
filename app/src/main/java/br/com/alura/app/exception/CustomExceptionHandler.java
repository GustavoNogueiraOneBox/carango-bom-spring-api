package br.com.alura.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MarcaInvalidaException.class)
    public ResponseEntity<String> handleMarcaInvalidaException(MarcaInvalidaException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: " + e.getMessage());
    }

    @ExceptionHandler(VeiculoInvalidoException.class)
    public ResponseEntity<String> handleVeiculoInvalidoException(VeiculoInvalidoException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: " + e.getMessage());
    }
}
