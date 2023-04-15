package br.com.alura.app.api.veiculo.model;

import br.com.alura.app.api.marca.model.Marca;
import br.com.alura.app.api.veiculo.controller.VeiculoForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Marca marca;
    private String modelo;
    private Integer ano;
    private BigDecimal valor;

    public Veiculo(VeiculoForm veiculoForm){
        this.marca = veiculoForm.getMarca();
        this.modelo = veiculoForm.getModelo();
        this.ano = veiculoForm.getAno();
        this.valor = veiculoForm.getValor();
    }
}