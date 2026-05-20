package com.concessionaria.api.service;

import com.concessionaria.api.entity.Cliente;
import com.concessionaria.api.entity.Veiculo;
import com.concessionaria.api.exception.BusinessException;
import com.concessionaria.api.repository.VeiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ClienteService clienteService;

    public VeiculoService(VeiculoRepository veiculoRepository, ClienteService clienteService) {
        this.veiculoRepository = veiculoRepository;
        this.clienteService = clienteService;
    }

    public Veiculo cadastrar(Veiculo veiculo) {
        if (veiculoRepository.findByPlaca(veiculo.getPlaca()).isPresent()) {
            throw new BusinessException("Já existe um veículo cadastrado com esta placa.", HttpStatus.CONFLICT); // HTTP 409
        }
        validarRegraVenda(veiculo);
        Cliente cliente = clienteService.buscarPorId(veiculo.getCliente().getId());
        veiculo.setCliente(cliente);

        return veiculoRepository.save(veiculo);
    }
    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }
    public List<Veiculo> buscarPorTermo(String termo) {
        var veiculoPorPlaca = veiculoRepository.findByPlaca(termo);
        if (veiculoPorPlaca.isPresent()) {
            return List.of(veiculoPorPlaca.get());
        }
        return veiculoRepository.findByMarcaContainingIgnoreCaseOrModeloContainingIgnoreCase(termo, termo);
    }

    public Veiculo atualizar(UUID id, Veiculo veiculoAtualizado) {
        Veiculo veiculoExistente = veiculoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Veículo não encontrado para atualização.", HttpStatus.NOT_FOUND));

        if (!veiculoExistente.getPlaca().equalsIgnoreCase(veiculoAtualizado.getPlaca()) &&
                veiculoRepository.findByPlaca(veiculoAtualizado.getPlaca()).isPresent()) {
            throw new BusinessException("A nova placa informada já pertence a outro veículo.", HttpStatus.CONFLICT);
        }

        validarRegraVenda(veiculoAtualizado);

        veiculoExistente.setMarca(veiculoAtualizado.getMarca());
        veiculoExistente.setModelo(veiculoAtualizado.getModelo());
        veiculoExistente.setAno(veiculoAtualizado.getAno());
        veiculoExistente.setValor(veiculoAtualizado.getValor());
        veiculoExistente.setPlaca(veiculoAtualizado.getPlaca());
        veiculoExistente.setMaximoDesconto(veiculoAtualizado.getMaximoDesconto());
        veiculoExistente.setVendido(veiculoAtualizado.isVendido());
        veiculoExistente.setValorVenda(veiculoAtualizado.getValorVenda());

        return veiculoRepository.save(veiculoExistente);
    }
    public void deletar(UUID id) {
        if (!veiculoRepository.existsById(id)) {
            throw new BusinessException("Veículo não encontrado para remoção.", HttpStatus.NOT_FOUND);
        }
        veiculoRepository.deleteById(id);
    }
    private void validarRegraVenda(Veiculo veiculo) {
        if (veiculo.isVendido() && veiculo.getValorVenda() == null) {
            throw new BusinessException("O valor de venda é obrigatório quando o veículo está marcado como vendido.", HttpStatus.BAD_REQUEST); // HTTP 400
        }
    }
}