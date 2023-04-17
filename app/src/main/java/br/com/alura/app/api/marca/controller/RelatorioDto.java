package br.com.alura.app.api.marca.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RelatorioDto {
    private String marca;
    private Integer quantidade;
    private BigDecimal valor_venda;
}
