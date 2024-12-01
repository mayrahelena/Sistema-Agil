package com.sistemaagil.model;

import java.sql.Timestamp;
import java.util.Objects;

public class RelatorioMovimentacaoCaixa {
    private String tipoMovimentacao;
    private String descricao;
    private Timestamp dataMovimentacao;
    private double entrada;
    private double saida;

    // Construtor com validação
    public RelatorioMovimentacaoCaixa(String tipoMovimentacao, String descricao, Timestamp dataMovimentacao, double entrada, double saida) {
        this.tipoMovimentacao = tipoMovimentacao;
        this.descricao = descricao;
        this.dataMovimentacao = dataMovimentacao;
        this.entrada = entrada >= 0 ? entrada : 0;  // Validação para garantir que a entrada não seja negativa
        this.saida = saida >= 0 ? saida : 0;      // Validação para garantir que a saída não seja negativa
    }

    // Getters e Setters
    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Timestamp dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public double getEntrada() {
        return entrada;
    }

    public void setEntrada(double entrada) {
        if (entrada >= 0) {
            this.entrada = entrada;
        } else {
            throw new IllegalArgumentException("A entrada não pode ser negativa");
        }
    }

    public double getSaida() {
        return saida;
    }

    public void setSaida(double saida) {
        if (saida >= 0) {
            this.saida = saida;
        } else {
            throw new IllegalArgumentException("A saída não pode ser negativa");
        }
    }

    @Override
    public String toString() {
        return "Tipo: " + tipoMovimentacao + ", Descrição: " + descricao + ", Data: " + dataMovimentacao + ", Entrada: " + entrada + ", Saída: " + saida;
    }

    // Sobrescrevendo equals e hashCode para garantir comparações corretas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelatorioMovimentacaoCaixa that = (RelatorioMovimentacaoCaixa) o;
        return Double.compare(that.entrada, entrada) == 0 &&
                Double.compare(that.saida, saida) == 0 &&
                Objects.equals(tipoMovimentacao, that.tipoMovimentacao) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(dataMovimentacao, that.dataMovimentacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoMovimentacao, descricao, dataMovimentacao, entrada, saida);
    }
}

