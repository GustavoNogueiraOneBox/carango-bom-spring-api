package br.com.alura.app.api.marca.model;

import br.com.alura.app.api.marca.controller.MarcaForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    
    public Marca(MarcaForm marcaForm){
        this.nome = marcaForm.getNome();
    }
}
