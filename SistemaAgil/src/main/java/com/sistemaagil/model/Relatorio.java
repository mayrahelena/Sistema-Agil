package com.sistemaagil.model;


import java.util.Date;

public class Relatorio {
    // Tipos de Relatório
    public static final String TIPO_CAIXA = "Relatório de Caixa";
    public static final String TIPO_ESTOQUE = "Relatório de Estoque";
    public static final String TIPO_VENDAS = "Relatório de Vendas";
    public static final String TIPO_MOVIMENTACAO = "Relatório de Movimentação";
    public static final String TIPO_FECHAMENTO_CAIXA = "Relatório de Fechamento de Caixa";  // Novo tipo de relatório

    private int id;
    private String especificacoes;
    private String tipo;
    private Date data;

    // Construtores
    public Relatorio(String especificacoes, String tipo, Date data) {
        this.especificacoes = especificacoes;
        this.tipo = tipo;
        this.data = data;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(String especificacoes) {
        this.especificacoes = especificacoes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
