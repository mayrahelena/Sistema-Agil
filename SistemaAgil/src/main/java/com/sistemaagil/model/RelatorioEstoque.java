package com.sistemaagil.model;

public class RelatorioEstoque {
    private String nomeProduto;
    private int quantidadeEstoque;
    private int quantidadeMinima;

    // Construtores, getters e setters
    public RelatorioEstoque(String nomeProduto, int quantidadeEstoque, int quantidadeMinima) {
        this.nomeProduto = nomeProduto;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    @Override
    public String toString() {
        return "Produto: " + nomeProduto + ", Estoque: " + quantidadeEstoque + ", Mínimo: " + quantidadeMinima;
    }
}
