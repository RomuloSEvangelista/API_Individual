package com.concessionaria.api.repository;

import com.concessionaria.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByCpf(String cpf);

    java.util.List<Cliente> findByNomeContainingIgnoreCase(String nome);
}