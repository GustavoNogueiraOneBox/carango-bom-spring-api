package br.com.alura.app.api.marca.controller;

import br.com.alura.app.api.marca.model.Marca;
import br.com.alura.app.api.marca.service.MarcaService;
import br.com.alura.app.exception.MarcaInvalidaException;
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
import java.net.URI;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private ValidadorDeFormulario validadorDeFormulario;

    @PostMapping()
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody MarcaForm marcaForm, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) throws Exception {
        validadorDeFormulario.validarFormulario(bindingResult, MarcaInvalidaException.class);
        Marca marcaCadastrada = marcaService.cadastrar(marcaForm);
        URI uri = uriComponentsBuilder.path("/api/marca/{id}").buildAndExpand(marcaCadastrada.getId()).toUri();
        return ResponseEntity.created(uri).body(marcaCadastrada);
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
    public ResponseEntity<Marca> atualizarPorId(@PathVariable Long id, @Valid @RequestBody MarcaForm marcaForm, BindingResult bindingResult) throws Exception {
        validadorDeFormulario.validarFormulario(bindingResult, MarcaInvalidaException.class);
        Marca marcaAtualizada = marcaService.atualizarPorId(id, marcaForm);
        return ResponseEntity.ok(marcaAtualizada);
    }
    @GetMapping("/relatorio")
    public Page<RelatorioDto> gerarRelatorio(@PageableDefault(size = 30) Pageable pageable){
        return marcaService.relatorioDeVendasPorMarca(pageable);
    }

}
