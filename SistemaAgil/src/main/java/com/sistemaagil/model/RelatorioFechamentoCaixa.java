package com.sistemaagil.model;

import java.sql.Timestamp;
import java.util.Objects;

public class RelatorioFechamentoCaixa {
    private Timestamp dataFechamento;
    private double saldoInicial;
    private double saldoFinal;
    private double totalPix;
    private double totalDinheiro;
    private double totalCredito;
    private double totalDebito;

    // Construtor com validações
    public RelatorioFechamentoCaixa(Timestamp dataFechamento, double saldoInicial, double saldoFinal, 
                                    double totalPix, double totalDinheiro, double totalCredito, double totalDebito) {
        this.dataFechamento = dataFechamento;
        this.saldoInicial = saldoInicial >= 0 ? saldoInicial : 0;  // Validação para garantir saldo não negativo
        this.saldoFinal = saldoFinal >= 0 ? saldoFinal : 0;        // Validação para garantir saldo não negativo
        this.totalPix = totalPix >= 0 ? totalPix : 0;              // Validação para garantir valor não negativo
        this.totalDinheiro = totalDinheiro >= 0 ? totalDinheiro : 0; // Validação para garantir valor não negativo
        this.totalCredito = totalCredito >= 0 ? totalCredito : 0;  // Validação para garantir valor não negativo
        this.totalDebito = totalDebito >= 0 ? totalDebito : 0;     // Validação para garantir valor não negativo
    }

    // Getters e Setters
    public Timestamp getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Timestamp dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        if (saldoInicial >= 0) {
            this.saldoInicial = saldoInicial;
        } else {
            throw new IllegalArgumentException("O saldo inicial não pode ser negativo");
        }
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(double saldoFinal) {
        if (saldoFinal >= 0) {
            this.saldoFinal = saldoFinal;
        } else {
            throw new IllegalArgumentException("O saldo final não pode ser negativo");
        }
    }

    public double getTotalPix() {
        return totalPix;
    }

    public void setTotalPix(double totalPix) {
        if (totalPix >= 0) {
            this.totalPix = totalPix;
        } else {
            throw new IllegalArgumentException("O total Pix não pode ser negativo");
        }
    }

    public double getTotalDinheiro() {
        return totalDinheiro;
    }

    public void setTotalDinheiro(double totalDinheiro) {
        if (totalDinheiro >= 0) {
            this.totalDinheiro = totalDinheiro;
        } else {
            throw new IllegalArgumentException("O total Dinheiro não pode ser negativo");
        }
    }

    public double getTotalCredito() {
        return totalCredito;
    }

    public void setTotalCredito(double totalCredito) {
        if (totalCredito >= 0) {
            this.totalCredito = totalCredito;
        } else {
            throw new IllegalArgumentException("O total Crédito não pode ser negativo");
        }
    }

    public double getTotalDebito() {
        return totalDebito;
    }

    public void setTotalDebito(double totalDebito) {
        if (totalDebito >= 0) {
            this.totalDebito = totalDebito;
        } else {
            throw new IllegalArgumentException("O total Débito não pode ser negativo");
        }
    }

    @Override
    public String toString() {
        return "Data Fechamento: " + dataFechamento + ", Saldo Inicial: " + saldoInicial + ", Saldo Final: " + saldoFinal +
                ", Total Pix: " + totalPix + ", Total Dinheiro: " + totalDinheiro + ", Total Crédito: " + totalCredito + ", Total Débito: " + totalDebito;
    }

    // Sobrescrevendo equals e hashCode para garantir comparações corretas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelatorioFechamentoCaixa that = (RelatorioFechamentoCaixa) o;
        return Double.compare(that.saldoInicial, saldoInicial) == 0 &&
                Double.compare(that.saldoFinal, saldoFinal) == 0 &&
                Double.compare(that.totalPix, totalPix) == 0 &&
                Double.compare(that.totalDinheiro, totalDinheiro) == 0 &&
                Double.compare(that.totalCredito, totalCredito) == 0 &&
                Double.compare(that.totalDebito, totalDebito) == 0 &&
                Objects.equals(dataFechamento, that.dataFechamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataFechamento, saldoInicial, saldoFinal, totalPix, totalDinheiro, totalCredito, totalDebito);
    }
}
