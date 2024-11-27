package com.sistemaagil.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Caixa {
    private int id; // ID do caixa
    private LocalDateTime abertura;
    private LocalDateTime fechamento;
    private double saldoInicial;
    private double saldoFinal;
    private Map<String, Double> entradasPorFormaPagamento; // Total por forma de pagamento
    private Map<String, Double> saidasPorFormaPagamento; // Total por forma de saída

    public Caixa() {
        this.entradasPorFormaPagamento = new HashMap<>();
        this.saidasPorFormaPagamento = new HashMap<>();
    }

    // Getters e Setters
    public int getId() { return id; } // Método para obter o ID do caixa
    public void setId(int id) { this.id = id; }
    public LocalDateTime getAbertura() { return abertura; }
    public void setAbertura(LocalDateTime abertura) { this.abertura = abertura; }
    public LocalDateTime getFechamento() { return fechamento; }
    public void setFechamento(LocalDateTime fechamento) { this.fechamento = fechamento; }
    public double getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(double saldoInicial) { this.saldoInicial = saldoInicial; }
    public double getSaldoFinal() { return saldoFinal; }
    public void setSaldoFinal(double saldoFinal) { this.saldoFinal = saldoFinal; }

    // Método para registrar entrada de valores
    public void registrarEntrada(double valor, String metodoPagamento) {
        entradasPorFormaPagamento.merge(metodoPagamento, valor, Double::sum);
        saldoFinal += valor;
    }

    // Método para registrar saída de valores
    public void registrarSaida(double valor, String metodoPagamento) {
        saidasPorFormaPagamento.merge(metodoPagamento, valor, Double::sum);
        saldoFinal -= valor;
    }

    // Método para fechar o caixa
    public void fecharCaixa() {
        this.fechamento = LocalDateTime.now();
        System.out.println("Caixa fechado com saldo final: R$ " + saldoFinal);
        System.out.println("Total por método de pagamento:");
        entradasPorFormaPagamento.forEach((metodo, total) -> {
            System.out.println(metodo + ": R$ " + total);
        });
        saidasPorFormaPagamento.forEach((metodo, total) -> {
            System.out.println("Saída " + metodo + ": R$ " + total);
        });
    }
}