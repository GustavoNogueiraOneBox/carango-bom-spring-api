package br.com.alura.app.api.marca.controller;

import br.com.alura.app.api.marca.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
