package br.com.alura.app.exception;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FormularioInvalidoException.class)
    public ResponseEntity<String> handleFormularioInvalidoException(FormularioInvalidoException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    }

    @ExceptionHandler(MarcaInvalidaException.class)
    public ResponseEntity<String> handleMarcaInvalidaException(MarcaInvalidaException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    }

    @ExceptionHandler(VeiculoInvalidoException.class)
    public ResponseEntity<String> handleVeiculoInvalidoException(VeiculoInvalidoException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
    }

}
