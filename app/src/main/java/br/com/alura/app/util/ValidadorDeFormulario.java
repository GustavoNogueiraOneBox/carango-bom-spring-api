package br.com.alura.app.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ValidadorDeFormulario {

    public void validarFormulario(BindingResult bindingResult, Class<? extends Exception> exceptionType) throws Exception {
        if (bindingResult.hasErrors()) {
            throw exceptionType.getConstructor(String.class).newInstance("Formulário inválido");
            }
        }
    }
