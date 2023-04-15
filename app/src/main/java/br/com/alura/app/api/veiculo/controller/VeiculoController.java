package br.com.alura.app.api.veiculo.controller;

import br.com.alura.app.api.veiculo.model.Veiculo;
import br.com.alura.app.api.veiculo.service.VeiculoService;
import br.com.alura.app.exception.VeiculoInvalidoException;
import br.com.alura.app.util.ValidadorDeFormulario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/api/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private ValidadorDeFormulario validaFormulario;

    @PostMapping()
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody VeiculoForm veiculoForm, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) throws Exception {
        validaFormulario.validarFormulario(bindingResult, VeiculoInvalidoException.class);
        Veiculo veiculo = veiculoService.cadastrar(veiculoForm);
        URI uri = uriComponentsBuilder.path("/api/veiculo/{id}").buildAndExpand(veiculo.getId()).toUri();
        return ResponseEntity.created(uri).body(veiculo);
    }

    @GetMapping()
    public Page<Veiculo> listarTodos(@PageableDefault(size = 30) Pageable pageable) {
        return veiculoService.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    public Veiculo getVeiculo(@PathVariable Long id) throws VeiculoInvalidoException {
        return veiculoService.getVeiculo(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) throws VeiculoInvalidoException {
        if (id == null || id == 0) {
            throw new VeiculoInvalidoException("ID inválido");
        }
        veiculoService.deletarPorId(id);
        return ResponseEntity.ok("Veiculo de id '" + id + "' deletado");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Veiculo> atualizarPorId(@PathVariable Long id, @Valid @RequestBody VeiculoForm veiculoForm, BindingResult bindingResult) throws Exception {
        validaFormulario.validarFormulario(bindingResult, VeiculoInvalidoException.class);
        Veiculo veiculo = veiculoService.atualizarPorId(id, veiculoForm);
        return ResponseEntity.ok(veiculo);
    }

    @GetMapping("filtrarValor/{valorMin}-{valorMax}")
    public Page<Veiculo> listarFiltradoPorValor(@PageableDefault(size = 30) Pageable pageable, @PathVariable BigDecimal valorMin, @PathVariable BigDecimal valorMax) {
        return veiculoService.veiculosFiltradosPorValor(pageable, valorMin, valorMax);
    }

    @GetMapping("filtrarMarca/{nomeDaMarca}")
    public Page<Veiculo> listarFiltradoPorMarca(@PageableDefault(size = 30) Pageable pageable, @PathVariable String nomeDaMarca) {
        return veiculoService.veiculosFiltradosPorMarca(pageable, nomeDaMarca);
    }
}
