package br.com.alura.app.api.marca.controller;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MarcaForm {

    @NotNull @NotBlank @Size(min = 3)
    private String nome;
}
