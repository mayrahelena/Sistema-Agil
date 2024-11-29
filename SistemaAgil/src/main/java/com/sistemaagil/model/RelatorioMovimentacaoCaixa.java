
package com.sistemaagil.model;

import java.sql.Timestamp;

public class RelatorioMovimentacaoCaixa {
    private String tipoMovimentacao;
    private String descricao;
    private Timestamp dataMovimentacao;
    private double entrada;
    private double saida;

    // Construtores, getters e setters
    public RelatorioMovimentacaoCaixa(String tipoMovimentacao, String descricao, Timestamp dataMovimentacao, double entrada, double saida) {
        this.tipoMovimentacao = tipoMovimentacao;
        this.descricao = descricao;
        this.dataMovimentacao = dataMovimentacao;
        this.entrada = entrada;
        this.saida = saida;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipoMovimentacao + ", Descrição: " + descricao + ", Data: " + dataMovimentacao + ", Entrada: " + entrada + ", Saída: " + saida;
    }
}

