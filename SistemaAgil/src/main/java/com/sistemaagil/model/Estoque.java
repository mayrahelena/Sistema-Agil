package com.sistemaagil.model;

import java.util.Date;

public class Estoque {
    private int id;
    private String nome;
    private double preco;
    private double precoCusto;
    private double margemLucro;
    private Date validade;
    private int quantidade;
    private int quantidadeMinima;
    private String codigoBarras;  

    // Construtores
    public Estoque(String nome, double preco, double precoCusto, double margemLucro, Date validade, int quantidade, int quantidadeMinima, String codigoBarras) {
        this.nome = nome;
        this.preco = preco;
        this.precoCusto = precoCusto;
        this.margemLucro = margemLucro;
        this.validade = validade;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.codigoBarras = codigoBarras;
    }

    // Getters e Setters
    public int getId() { return id; }
    
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }

    public void setPreco(double preco) { this.preco = preco; }

    public double getPrecoCusto() { return precoCusto; }

    public void setPrecoCusto(double precoCusto) { this.precoCusto = precoCusto; }

    public double getMargemLucro() { return margemLucro; }

    public void setMargemLucro(double margemLucro) { this.margemLucro = margemLucro; }

    public Date getValidade() { return validade; }

    public void setValidade(Date validade) { this.validade = validade; }

    public int getQuantidade() { return quantidade; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public int getQuantidadeMinima() { return quantidadeMinima; }

    public void setQuantidadeMinima(int quantidadeMinima) { this.quantidadeMinima = quantidadeMinima; }

    public String getCodigoBarras() { return codigoBarras; }

    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }
}