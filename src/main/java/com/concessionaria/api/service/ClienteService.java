package com.concessionaria.api.service;

import com.concessionaria.api.entity.Cliente;
import com.concessionaria.api.exception.BusinessException;
import com.concessionaria.api.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrar(Cliente cliente) {
        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new BusinessException("Já existe um cliente cadastrado com este CPF.", HttpStatus.CONFLICT); // HTTP 409
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public List<Cliente> buscarPorNomeOuCpf(String termo) {
        var clientePorCpf = clienteRepository.findByCpf(termo);
        if (clientePorCpf.isPresent()) {
            return List.of(clientePorCpf.get());
        }
        return clienteRepository.findByNomeContainingIgnoreCase(termo);
    }
    public void deletar(UUID id) {
        if (!clienteRepository.existsById(id)) {
            throw new BusinessException("Cliente não encontrado para remoção.", HttpStatus.NOT_FOUND); // HTTP 404
        }
        clienteRepository.deleteById(id);
    }
    public Cliente buscarPorId(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente dono do veículo não encontrado.", HttpStatus.NOT_FOUND));
    }
}