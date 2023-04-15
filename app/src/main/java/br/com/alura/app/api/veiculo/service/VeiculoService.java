package br.com.alura.app.api.veiculo.service;

import br.com.alura.app.api.marca.model.Marca;
import br.com.alura.app.api.marca.repository.MarcaRepository;
import br.com.alura.app.api.veiculo.controller.VeiculoForm;
import br.com.alura.app.api.veiculo.model.Veiculo;
import br.com.alura.app.api.veiculo.repository.VeiculoRepository;
import br.com.alura.app.exception.MarcaInvalidaException;
import br.com.alura.app.exception.VeiculoInvalidoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    public Veiculo getVeiculo(Long id) throws VeiculoInvalidoException {
        return veiculoRepository.findById(id).orElseThrow(() -> new VeiculoInvalidoException("Veiculo de id: " + id + " não encontrado"));
    }

    public Veiculo cadastrar(VeiculoForm veiculoForm) throws MarcaInvalidaException {
        Marca marca = marcaRepository.findById(veiculoForm.getMarca().getId()).orElseThrow(() -> new MarcaInvalidaException("Marca de id: " + veiculoForm.getMarca().getId() + " não encontrada"));
        veiculoForm.setMarca(marca);
        return veiculoRepository.save(new Veiculo(veiculoForm));
    }

    public Page<Veiculo> listarTodos(Pageable pageable) {
        return veiculoRepository.findAll(pageable);
    }

    public void deletarPorId(Long id) throws VeiculoInvalidoException {
        Veiculo veiculo = getVeiculo(id);
        veiculoRepository.delete(veiculo);
    }

    public Veiculo atualizarPorId(Long id, VeiculoForm veiculoForm) throws VeiculoInvalidoException {
        Veiculo veiculo = getVeiculo(id);
        BeanUtils.copyProperties(veiculoForm, veiculo);
        return veiculoRepository.save(veiculo);
    }

    public Page<Veiculo> veiculosFiltradosPorValor(Pageable pageable, BigDecimal valorMin, BigDecimal valorMax) {
        return veiculoRepository.veiculosFiltradosPorValor(pageable, valorMin, valorMax);
    }

    public Page<Veiculo> veiculosFiltradosPorMarca(Pageable pageable, String nomeDaMarca) {
        return veiculoRepository.veiculosFiltradosPorMarca(pageable, nomeDaMarca);
    }
}
