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

import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    public void cadastrar(VeiculoForm veiculoForm){
        Marca marca = marcaRepository.findById(veiculoForm.getMarca().getId()).get();
        veiculoForm.setMarca(marca);
        Veiculo veiculo = new Veiculo();
        BeanUtils.copyProperties(veiculoForm, veiculo);
        veiculoRepository.save(veiculo);
    }

    public Page<Veiculo> listarTodos(Pageable pageable){
        return veiculoRepository.findAll(pageable);
    }

    public void deletarPorId(VeiculoDto veiculoDto){
        veiculoRepository.deleteById(veiculoDto.getId());
    }
    public VeiculoForm get(Long id) {
        Optional<Veiculo> obj = veiculoRepository.findById(id);
        Veiculo veiculo = obj.orElse(new Veiculo());

        return new VeiculoForm(veiculo);
    }
}
