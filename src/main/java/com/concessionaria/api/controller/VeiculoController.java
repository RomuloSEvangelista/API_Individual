package com.concessionaria.api.controller;

import com.concessionaria.api.entity.Veiculo;
import com.concessionaria.api.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/veiculo") 
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<Veiculo> cadastrar(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.cadastrar(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listarOuBuscar(
            @RequestParam(required = false) String placa,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo) {

        if (placa != null && !placa.isBlank()) {
            return ResponseEntity.ok(veiculoService.buscarPorTermo(placa));
        }
        if (marca != null && !marca.isBlank()) {
            return ResponseEntity.ok(veiculoService.buscarPorTermo(marca));
        }
        if (modelo != null && !modelo.isBlank()) {
            return ResponseEntity.ok(veiculoService.buscarPorTermo(modelo));
        }
        return ResponseEntity.ok(veiculoService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable UUID id, @RequestBody Veiculo veiculo) {
        Veiculo veiculoAtualizado = veiculoService.atualizar(id, veiculo);
        return ResponseEntity.ok(veiculoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        veiculoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}