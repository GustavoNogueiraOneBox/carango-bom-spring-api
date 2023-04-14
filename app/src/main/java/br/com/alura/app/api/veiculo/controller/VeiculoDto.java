package br.com.alura.app.api.veiculo.controller;

import br.com.alura.app.api.marca.model.Marca;
import br.com.alura.app.api.veiculo.model.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDto {
    private Long id;
    private String modelo;
    private Integer ano;
    private BigDecimal valor;


    public VeiculoDto(Veiculo veiculo){
        this.id = veiculo.getId();
        this.modelo = veiculo.getModelo();
        this.ano = veiculo.getAno();
        this.valor = veiculo.getValor();
    }
}
