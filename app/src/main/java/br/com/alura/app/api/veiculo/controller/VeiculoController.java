package br.com.alura.app.api.veiculo.controller;

import br.com.alura.app.api.veiculo.model.Veiculo;
import br.com.alura.app.api.veiculo.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    @Transactional
    public ResponseEntity<VeiculoForm> cadastrar(@Valid @RequestBody VeiculoForm veiculoForm){
        veiculoService.cadastrar(veiculoForm);
        return ResponseEntity.ok(veiculoForm);
    }

    @GetMapping
    public Page<Veiculo> listarTodos(@PageableDefault(size = 30) Pageable pageable){
        return veiculoService.listarTodos(pageable);
    }

    @GetMapping("detalhar/{id}")
    public Veiculo getVeiculo(@PathVariable Long id){
        return veiculoService.getVeiculo(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        veiculoService.deletarPorId(id);
        return ResponseEntity.ok("Veiculo do id '" + id + "' deletado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoForm> atualizarPorId(@PathVariable Long id, @Valid @RequestBody VeiculoForm veiculoForm){
        veiculoService.atualizarPorId(id, veiculoForm);
        return ResponseEntity.ok(veiculoForm);
    }
    @GetMapping("filtrar/{valorMin}-{valorMax}")
    public Page<Veiculo> listarFiltradoPorValor(@PageableDefault(size = 30) Pageable pageable ,@PathVariable BigDecimal valorMin, @PathVariable BigDecimal valorMax){
        return veiculoService.veiculosFiltradosPorValor(pageable, valorMin, valorMax);
    }
    @GetMapping("filtrar/{nomeDaMarca}")
    public Page<Veiculo> listarFiltradoPorMarca(@PageableDefault(size = 30) Pageable pageable ,@PathVariable String nomeDaMarca) {
        return veiculoService.veiculosFiltradosPorMarca(pageable, nomeDaMarca);
    }
}
