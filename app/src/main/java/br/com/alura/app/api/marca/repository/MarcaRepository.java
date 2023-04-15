package br.com.alura.app.api.marca.repository;

import br.com.alura.app.api.marca.controller.RelatorioDto;
import br.com.alura.app.api.marca.model.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    Marca findFirstByNome(String nome);

//    @Query(value = "select nome as marca, count(marca_id) as quantidade, sum(valor) as valor_venda from marca join veiculo v on marca.id = v.marca_id group by marca_id", nativeQuery = true)

    @Query(value = "SELECT nome AS marca, COUNT(marca_id) AS quantidade, SUM(valor) AS valor_venda FROM marca JOIN veiculo v ON marca.id = v.marca_id GROUP BY marca_id",
            countQuery = "SELECT COUNT(*) FROM (SELECT nome, COUNT(marca_id) AS quantidade, SUM(valor) AS valor_venda FROM marca JOIN veiculo v ON marca.id = v.marca_id GROUP BY marca_id) AS subquery",
            nativeQuery = true)
    Page<RelatorioDto> relatorioDeVendasPorMarca(Pageable pageable);
}
