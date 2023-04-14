package br.com.alura.app.api.veiculo.controller;

import br.com.alura.app.api.marca.model.Marca;
import br.com.alura.app.api.veiculo.model.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoForm {
    @NotNull
    private Marca marca;

    @Size(min = 2) @NotNull @NotBlank
    private String modelo;

    @NotNull @Min(value = 0)
    private Integer ano;

    @NotNull @DecimalMin(value = "0.0") @Digits(integer = 9, fraction = 2)
    private BigDecimal valor;


    public VeiculoForm(Veiculo veiculo){
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.ano = veiculo.getAno();
        this.valor = veiculo.getValor();
    }
}
