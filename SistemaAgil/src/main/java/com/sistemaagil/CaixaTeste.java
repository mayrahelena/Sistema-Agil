package com.sistemaagil;

import com.sistemaagil.model.Caixa;
import com.sistemaagil.service.CaixaService;

import java.sql.SQLException;
import java.util.Scanner;

public class CaixaTeste {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CaixaService caixaService = new CaixaService();

        try {
            System.out.println("=== Teste do Módulo de Caixa ===");
            
            // Abrindo o caixa com saldo inicial
            Caixa caixa = caixaService.abrirCaixa(100.00); 
            System.out.println("Caixa aberto com ID: " + caixa.getId()); // Usando getId()

            // Registrando entradas no caixa
            for (int i = 1; i <= 3; i++) {
                double valorVenda = 50.00 + (i * 10); 
                caixa.registrarEntrada(valorVenda, "Dinheiro"); // Método da classe Caixa
                System.out.println("Venda registrada: R$ " + valorVenda);
            }

            // Fechando o caixa
            caixa.fecharCaixa(); // Chamada ao método fecharCaixa da classe Caixa
            System.out.println("Caixa fechado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro durante os testes do caixa: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Erro no estado do sistema: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}