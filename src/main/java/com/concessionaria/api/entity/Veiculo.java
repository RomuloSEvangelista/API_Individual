package com.concessionaria.api.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false, unique = true)
    private String placa;

    @Column(nullable = false)
    private Double maximoDesconto;

    @Column(nullable = false)
    private boolean vendido;

    private Double valorVenda;




    public Veiculo() {
    }

    public Veiculo(UUID id, Cliente cliente, String marca, String modelo, Integer ano, Double valor, String placa, Double maximoDesconto, boolean vendido, Double valorVenda) {
        this.id = id;
        this.cliente = cliente;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.valor = valor;
        this.placa = placa;
        this.maximoDesconto = maximoDesconto;
        this.vendido = vendido;
        this.valorVenda = valorVenda;
    }


    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public Double getMaximoDesconto() { return maximoDesconto; }
    public void setMaximoDesconto(Double maximoDesconto) { this.maximoDesconto = maximoDesconto; }

    public boolean isVendido() { return vendido; }
    public void setVendido(boolean vendido) { this.vendido = vendido; }

    public Double getValorVenda() { return valorVenda; }
    public void setValorVenda(Double valorVenda) { this.valorVenda = valorVenda; }
}