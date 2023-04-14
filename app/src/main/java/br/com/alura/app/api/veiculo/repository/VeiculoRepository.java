package br.com.alura.app.api.veiculo.repository;

import br.com.alura.app.api.veiculo.model.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Query("SELECT v FROM Veiculo v WHERE v.valor BETWEEN :valorMin AND :valorMax")
    Page<Veiculo> veiculosFiltradosPorValor(Pageable pageable, @Param("valorMin")BigDecimal valorMin, @Param("valorMax") BigDecimal valorMax);

    @Query("SELECT v from Veiculo v WHERE v.marca.nome = :marca")
    Page<Veiculo> veiculosFiltradosPorMarca(Pageable pageable, @Param("marca") String nomeDaMarca);
}
