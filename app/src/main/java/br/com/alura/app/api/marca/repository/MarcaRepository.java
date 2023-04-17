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

    @Query(name = "preencher_relatorio_dto", nativeQuery = true)
    Page<RelatorioDto> relatorioDeVendasPorMarca(Pageable pageable);
}
