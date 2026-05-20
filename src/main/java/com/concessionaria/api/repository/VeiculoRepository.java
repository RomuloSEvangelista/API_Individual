package com.concessionaria.api.repository;

import com.concessionaria.api.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {

    Optional<Veiculo> findByPlaca(String placa);

    java.util.List<Veiculo> findByMarcaContainingIgnoreCaseOrModeloContainingIgnoreCase(String marca, String modelo);
}