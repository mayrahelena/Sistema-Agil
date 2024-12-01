package com.sistemaagil.model;

import java.util.Objects;

public class RelatorioEstoque {
    private String nomeProduto;
    private int quantidadeEstoque;
    private int quantidadeMinima;

    // Construtor
    public RelatorioEstoque(String nomeProduto, int quantidadeEstoque, int quantidadeMinima) {
        this.nomeProduto = nomeProduto;
        this.quantidadeEstoque = quantidadeEstoque >= 0 ? quantidadeEstoque : 0; // Validação para evitar estoque negativo
        this.quantidadeMinima = quantidadeMinima >= 0 ? quantidadeMinima : 0; // Validação para evitar quantidade mínima negativa
    }

    // Getters e Setters
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
        if (quantidadeEstoque >= 0) {
            this.quantidadeEstoque = quantidadeEstoque;
        } else {
            throw new IllegalArgumentException("A quantidade de estoque não pode ser negativa");
        }
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        if (quantidadeMinima >= 0) {
            this.quantidadeMinima = quantidadeMinima;
        } else {
            throw new IllegalArgumentException("A quantidade mínima não pode ser negativa");
        }
    }

    // Método toString para imprimir informações de forma legível
    @Override
    public String toString() {
        return "Produto: " + nomeProduto + ", Estoque: " + quantidadeEstoque + ", Mínimo: " + quantidadeMinima;
    }

    // Sobrescrevendo equals e hashCode para garantir comparações e inserção correta em coleções
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelatorioEstoque that = (RelatorioEstoque) o;
        return quantidadeEstoque == that.quantidadeEstoque &&
               quantidadeMinima == that.quantidadeMinima &&
               Objects.equals(nomeProduto, that.nomeProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeProduto, quantidadeEstoque, quantidadeMinima);
    }
}
