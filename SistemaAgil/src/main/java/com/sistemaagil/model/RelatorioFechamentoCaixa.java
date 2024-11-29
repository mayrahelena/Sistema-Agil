
package com.sistemaagil.model;

import java.sql.Timestamp;

public class RelatorioFechamentoCaixa {
    private Timestamp dataFechamento;
    private double saldoInicial;
    private double saldoFinal;
    private double totalPix;
    private double totalDinheiro;
    private double totalCredito;
    private double totalDebito;

    // Construtores, getters e setters
    public RelatorioFechamentoCaixa(Timestamp dataFechamento, double saldoInicial, double saldoFinal, double totalPix, double totalDinheiro, double totalCredito, double totalDebito) {
        this.dataFechamento = dataFechamento;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoFinal;
        this.totalPix = totalPix;
        this.totalDinheiro = totalDinheiro;
        this.totalCredito = totalCredito;
        this.totalDebito = totalDebito;
    }

    @Override
    public String toString() {
        return "Data Fechamento: " + dataFechamento + ", Saldo Inicial: " + saldoInicial + ", Saldo Final: " + saldoFinal +
                ", Total Pix: " + totalPix + ", Total Dinheiro: " + totalDinheiro + ", Total Crédito: " + totalCredito + ", Total Débito: " + totalDebito;
    }
}
