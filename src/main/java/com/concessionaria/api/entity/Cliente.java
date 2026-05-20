package com.concessionaria.api.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String email;





    public Cliente() {
    }

    public Cliente(UUID id, String nome, String telefone, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
    }




    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}