package br.com.alura.app.api.marca.service;

import br.com.alura.app.api.marca.controller.MarcaForm;
import br.com.alura.app.api.marca.model.Marca;
import br.com.alura.app.api.marca.repository.MarcaRepository;
import br.com.alura.app.api.veiculo.controller.VeiculoForm;
import br.com.alura.app.api.veiculo.model.Veiculo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Marca> listarMarcas(Pageable pageable){
        return marcaRepository.findAll(pageable);
    }

    public void deletarPorId(Long id){
        marcaRepository.deleteById(id);
    }

    public Marca getMarca(Long id) {
        return marcaRepository.findById(id).orElseThrow(() -> new RuntimeException("Marca não encontrada"));
    }

    public void atualizarPorId(Long id, MarcaForm marcaForm){
        Marca marca = getMarca(id);
        BeanUtils.copyProperties(marcaForm, marca);
        marcaRepository.save(marca);
    }
}
