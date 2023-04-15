package br.com.alura.app.api.marca.controller;

import br.com.alura.app.api.marca.model.Marca;
import br.com.alura.app.api.marca.service.MarcaService;
import br.com.alura.app.exception.MarcaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @PostMapping()
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody MarcaForm marcaForm, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) throws MarcaInvalidaException {
        if (bindingResult.hasErrors()) {
            throw new MarcaInvalidaException("Campo inválido");
        }
        Marca marca = marcaService.cadastrar(marcaForm);
        URI uri = uriComponentsBuilder.path("/api/marca/{id}").buildAndExpand(marca.getId()).toUri();
        return ResponseEntity.created(uri).body(marca);
    }
    @GetMapping()
    public Page<Marca> listarTodas(@PageableDefault(size = 30) Pageable pageable){
        return marcaService.listarMarcas(pageable);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) throws MarcaInvalidaException {
        if(id == null || id == 0){
            throw new MarcaInvalidaException("ID inválido");
        }
        marcaService.deletarPorId(id);
        return ResponseEntity.ok("Marca de id '" + id + "' deletada");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Marca> atualizarPorId(@PathVariable Long id, @Valid @RequestBody MarcaForm marcaForm, BindingResult bindingResult) throws MarcaInvalidaException {
        if (bindingResult.hasErrors()) {
            throw new MarcaInvalidaException("Campo inválido");
        }
        Marca marca = marcaService.atualizarPorId(id, marcaForm);
        return ResponseEntity.ok(marca);
    }
    @GetMapping("/relatorio")
    public Page<RelatorioDto> gerarRelatorio(@PageableDefault(size = 30) Pageable pageable){
        return marcaService.relatorioDeVendasPorMarca(pageable);
    }

}
