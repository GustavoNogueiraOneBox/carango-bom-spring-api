package br.com.alura.app.api.veiculo.service;

import br.com.alura.app.api.marca.model.Marca;
import br.com.alura.app.api.marca.repository.MarcaRepository;
import br.com.alura.app.api.veiculo.controller.VeiculoDto;
import br.com.alura.app.api.veiculo.controller.VeiculoForm;
import br.com.alura.app.api.veiculo.model.Veiculo;
import br.com.alura.app.api.veiculo.repository.VeiculoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    public Veiculo getVeiculo(Long id) {
        return veiculoRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));
    }

    public void cadastrar(VeiculoForm veiculoForm){
        Marca marca = marcaRepository.findById(veiculoForm.getMarca().getId()).orElseThrow(() -> new RuntimeException("Marca não encontrada"));
        veiculoForm.setMarca(marca);
        Veiculo veiculo = new Veiculo();
        BeanUtils.copyProperties(veiculoForm, veiculo);
        veiculoRepository.save(veiculo);
    }

    public Page<Veiculo> listarTodos(Pageable pageable){
        return veiculoRepository.findAll(pageable);
    }

    public void deletarPorId(Long id){
        veiculoRepository.deleteById(id);
    }

    public void atualizarPorId(Long id, VeiculoForm veiculoForm){
        Veiculo veiculo = getVeiculo(id);
        BeanUtils.copyProperties(veiculoForm, veiculo);
        veiculoRepository.save(veiculo);
    }

    public Page<Veiculo> veiculosFiltradosPorValor(Pageable pageable, BigDecimal valorMin, BigDecimal valorMax){
        return veiculoRepository.veiculosFiltradosPorValor(pageable, valorMin, valorMax);
    }
    public Page<Veiculo> veiculosFiltradosPorMarca(Pageable pageable, String nomeDaMarca) {
        return veiculoRepository.veiculosFiltradosPorMarca(pageable, nomeDaMarca);
    }
}
