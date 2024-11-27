package com.sistemaagil.model;

import java.sql.Timestamp;

public class Relatorio {
    private int id;
    private String conteudo;
    private Timestamp dataGeracao;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Timestamp getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(Timestamp dataGeracao) {
        this.dataGeracao = dataGeracao;
    }
}