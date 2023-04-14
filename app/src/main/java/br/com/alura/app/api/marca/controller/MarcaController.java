package br.com.alura.app.api.marca.controller;

import br.com.alura.app.api.marca.model.Marca;
import br.com.alura.app.api.marca.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @PostMapping
    @Transactional
    public ResponseEntity<MarcaForm> cadastrar(@Valid @RequestBody MarcaForm marcaForm){
        marcaService.cadastrar(marcaForm);
        return ResponseEntity.ok(marcaForm);

    }
    @GetMapping
    public Page<Marca> listarTodas(@PageableDefault(size = 30) Pageable pageable){
        return marcaService.listarMarcas(pageable);
    }

    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id){
        marcaService.deletarPorId(id);
        return ResponseEntity.ok("Marca de id '" + id + "' deletada");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MarcaForm> atualizarPorId(@PathVariable Long id, @RequestBody MarcaForm marcaForm){
        marcaService.atualizarPorId(id, marcaForm);
        return ResponseEntity.ok(marcaForm);
    }
}
