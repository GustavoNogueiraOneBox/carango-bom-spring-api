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
import java.util.List;

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
    public Page<VeiculoForm> listar(@PageableDefault(size = 30) Pageable pageable){
        Page<Veiculo> veiculos = veiculoService.listarTodos(pageable);
        return veiculos.map(Veiculo::converter);
    }

    @DeleteMapping
    public ResponseEntity<VeiculoDto> deletar(@Valid @RequestBody VeiculoDto veiculoDto){
        veiculoService.deletarPorId(veiculoDto);
        return ResponseEntity.ok(veiculoDto);
    }

}
