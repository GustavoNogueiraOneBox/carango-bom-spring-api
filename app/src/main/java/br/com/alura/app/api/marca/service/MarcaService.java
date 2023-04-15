package br.com.alura.app.api.marca.service;

import br.com.alura.app.api.marca.controller.MarcaForm;
import br.com.alura.app.api.marca.controller.RelatorioDto;
import br.com.alura.app.api.marca.model.Marca;
import br.com.alura.app.api.marca.repository.MarcaRepository;
import br.com.alura.app.exception.MarcaInvalidaException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public Marca cadastrar(MarcaForm marcaForm) throws MarcaInvalidaException {
        Marca marca = marcaRepository.findFirstByNome(marcaForm.getNome());
        if (marca != null && marca.getNome().equals(marcaForm.getNome().toUpperCase())) {
            throw new MarcaInvalidaException("Marca já cadastrada no banco");
        }
        return marcaRepository.save(new Marca(marcaForm));
    }

    public Page<Marca> listarMarcas(Pageable pageable) {
        return marcaRepository.findAll(pageable);
    }

    public void deletarPorId(Long id) throws MarcaInvalidaException {
        Marca marca = getMarca(id);
        marcaRepository.delete(marca);
    }

    public Marca getMarca(Long id) throws MarcaInvalidaException {
        return marcaRepository.findById(id).orElseThrow(() -> new MarcaInvalidaException("Marca de id: " + id + " não encontrada"));
    }

    public Marca atualizarPorId(Long id, MarcaForm marcaForm) throws MarcaInvalidaException {
        Marca marca = getMarca(id);
        BeanUtils.copyProperties(marcaForm, marca);
        return marcaRepository.save(marca);
    }
    public Page<RelatorioDto> relatorioDeVendasPorMarca(Pageable pageable){
        return marcaRepository.relatorioDeVendasPorMarca(pageable);
    }
}
