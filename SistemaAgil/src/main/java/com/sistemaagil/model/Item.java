package com.sistemaagil.model;

public class Item {
    private String codigoDeBarras;
    private String nome;
    private double preco;
    private int quantidade;

    // Construtor
    public Item(String codigoDeBarras, String nome, double preco, int quantidade) {
        this.codigoDeBarras = codigoDeBarras;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Método para calcular o subtotal de um item
    public double calcularSubtotal() {
        return preco * quantidade;
    }

    // Método toString para exibir o item de forma legível
    @Override
    public String toString() {
        return nome + " (Código: " + codigoDeBarras + ") - R$ " + preco + " x " + quantidade;
    }
}