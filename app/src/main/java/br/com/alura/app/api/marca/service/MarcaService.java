package br.com.alura.app.api.marca.service;

import br.com.alura.app.api.marca.controller.MarcaForm;
import br.com.alura.app.api.marca.model.Marca;
import br.com.alura.app.api.marca.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaService {
    
    @Autowired
    private MarcaRepository marcaRepository;
    
    public void cadastrar(MarcaForm marcaForm){
        if(marcaRepository.findFirstByNome(marcaForm.getNome()).getNome().equals(marcaForm.getNome())){
            throw new RuntimeException("Exception");
        }
        marcaRepository.save(new Marca(marcaForm));
    }
}
