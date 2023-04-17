package br.com.alura.app.api.marca.model;

import br.com.alura.app.api.marca.controller.MarcaForm;
import br.com.alura.app.api.marca.controller.RelatorioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@NamedNativeQuery(
        name = "preencher_relatorio_dto",
        query =
                "select nome as marca, count(marca_id) as quantidade, sum(valor) as valor_venda from marca join veiculo v on marca.id = v.marca_id group by marca_id",
        resultSetMapping = "relatorio_dto"
)
@SqlResultSetMapping(
        name = "relatorio_dto",
        classes = @ConstructorResult(
                targetClass = RelatorioDto.class,
                columns = {
                        @ColumnResult(name = "marca", type = String.class),
                        @ColumnResult(name = "quantidade", type = Integer.class),
                        @ColumnResult(name = "valor_venda", type = BigDecimal.class)
                }
        )
)
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    
    public Marca(MarcaForm marcaForm){
        this.nome = marcaForm.getNome().toUpperCase();
    }
}
