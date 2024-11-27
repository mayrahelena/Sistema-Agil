package com.sistemaagil.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private List<Item> itensVenda;
    private double totalVenda;
    private Caixa caixa;

    public Venda(Caixa caixa) {
        this.itensVenda = new ArrayList<>();
        this.totalVenda = 0.0;
        this.caixa = caixa;
    }

    public void adicionarItem(Item item) {
        itensVenda.add(item);
    }

    public void calcularTotal() {
        totalVenda = itensVenda.stream()
                .mapToDouble(Item::calcularSubtotal)
                .sum();
    }

    public void finalizarVenda(double valorPago, List<String> metodosPagamento) throws SQLException {
        calcularTotal();
        
        if (valorPago >= totalVenda) {
            for (String metodo : metodosPagamento) {
                caixa.registrarEntrada(totalVenda / metodosPagamento.size(), metodo); // Divide o total entre os métodos
            }

            double troco = valorPago - totalVenda;
            if (troco > 0) {
                System.out.println("Troco: R$ " + troco);
            }
            System.out.println("Venda finalizada com sucesso!");
        } else {
            System.out.println("Valor insuficiente para pagar a venda.");
        }
        
        itensVenda.clear();
    }

    public double getTotalVenda() {
        return totalVenda;
    }

    public List<Item> getItensVenda() {
        return itensVenda;
    }
}