package com.sistemaagil.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Caixa {
    private int id;
    private LocalDateTime abertura;
    private LocalDateTime fechamento;
    private double saldoInicial;
    private double saldoFinal;
    private Map<String, Double> entradasPorFormaPagamento = new HashMap<>();
    private Map<String, Double> saidasPorFormaPagamento = new HashMap<>();
    private double limiteCaixa;
    private String status;
    private int idFuncionario;

    // Construtor vazio
    public Caixa() {}

    // Construtor com parâmetros
    public Caixa(int id, LocalDateTime abertura, LocalDateTime fechamento, double saldoInicial, double saldoFinal,
                 Map<String, Double> entradasPorFormaPagamento, Map<String, Double> saidasPorFormaPagamento,
                 double limiteCaixa, String status, int idFuncionario) {
        this.id = id;
        this.abertura = abertura;
        this.fechamento = fechamento;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoFinal;
        this.entradasPorFormaPagamento = entradasPorFormaPagamento != null ? entradasPorFormaPagamento : new HashMap<>();
        this.saidasPorFormaPagamento = saidasPorFormaPagamento != null ? saidasPorFormaPagamento : new HashMap<>();
        this.limiteCaixa = limiteCaixa;
        this.status = status;
        this.idFuncionario = idFuncionario;
    }

    // Métodos para registrar entradas e saídas
    public void registrarEntrada(double valor, String formaPagamento) {
        entradasPorFormaPagamento.put(formaPagamento, 
            entradasPorFormaPagamento.getOrDefault(formaPagamento, 0.0) + valor);
    }

    public void registrarSaida(double valor, String formaPagamento) {
        saidasPorFormaPagamento.put(formaPagamento, 
            saidasPorFormaPagamento.getOrDefault(formaPagamento, 0.0) + valor);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getAbertura() {
        return abertura;
    }

    public void setAbertura(LocalDateTime abertura) {
        this.abertura = abertura;
    }

    public LocalDateTime getFechamento() {
        return fechamento;
    }

    public void setFechamento(LocalDateTime fechamento) {
        this.fechamento = fechamento;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public Map<String, Double> getEntradasPorFormaPagamento() {
        return entradasPorFormaPagamento;
    }

    public void setEntradasPorFormaPagamento(Map<String, Double> entradasPorFormaPagamento) {
        this.entradasPorFormaPagamento = entradasPorFormaPagamento;
    }

    public Map<String, Double> getSaidasPorFormaPagamento() {
        return saidasPorFormaPagamento;
    }

    public void setSaidasPorFormaPagamento(Map<String, Double> saidasPorFormaPagamento) {
        this.saidasPorFormaPagamento = saidasPorFormaPagamento;
    }

    public double getLimiteCaixa() {
        return limiteCaixa;
    }

    public void setLimiteCaixa(double limiteCaixa) {
        this.limiteCaixa = limiteCaixa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}

 
