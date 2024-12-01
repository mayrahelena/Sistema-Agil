package com.sistemaagil.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Relatorio {
    // Tipos de Relatório
    public static final String TIPO_CAIXA = "Relatório de Caixa";
    public static final String TIPO_ESTOQUE = "Relatório de Estoque";
    public static final String TIPO_VENDAS = "Relatório de Vendas";
    public static final String TIPO_MOVIMENTACAO = "Relatório de Movimentação";
    public static final String TIPO_FECHAMENTO_CAIXA = "Relatório de Fechamento de Caixa";

    private int id;
    private String especificacoes;
    private String tipo;
    private LocalDateTime data;

    // Construtores
    public Relatorio(String especificacoes, String tipo, LocalDateTime data) {
        if (especificacoes == null || tipo == null || data == null) {
            throw new IllegalArgumentException("Nenhum campo pode ser nulo.");
        }
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
        if (especificacoes == null) {
            throw new IllegalArgumentException("Especificações não podem ser nulas.");
        }
        this.especificacoes = especificacoes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo não pode ser nulo.");
        }
        this.tipo = tipo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        if (data == null) {
            throw new IllegalArgumentException("Data não pode ser nula.");
        }
        this.data = data;
    }

    // Método toString refatorado para maior clareza
    @Override
    public String toString() {
        return String.format("Relatorio{id=%d, especificacoes='%s', tipo='%s', data=%s}",
                             id, especificacoes, tipo, data);
    }

    // Métodos equals e hashCode para comparar objetos Relatorio
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relatorio relatorio = (Relatorio) o;
        return id == relatorio.id && 
               Objects.equals(especificacoes, relatorio.especificacoes) &&
               Objects.equals(tipo, relatorio.tipo) &&
               Objects.equals(data, relatorio.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, especificacoes, tipo, data);
    }
}
