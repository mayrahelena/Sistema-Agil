/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaagil;

/**
 *
 * @author mayhe
 */
public class CaixaTest {
        public static void main(String[] args) {
        CaixaService caixaService = new CaixaService();

        try {
            System.out.println("=== TESTE DO SISTEMA DE CAIXA ===");

            // Teste 1: Abrir um novo caixa
            System.out.println("\n[1] Abrindo novo caixa...");
            Caixa caixa = caixaService.abrirCaixa(100.0); // Saldo inicial de R$ 100,00
            System.out.println("Caixa aberto com sucesso!");
            System.out.println("ID do Caixa: " + caixa.getId());
            System.out.println("Saldo Inicial: R$ " + caixa.getSaldoInicial());

            // Teste 2: Registrar entradas no caixa
            System.out.println("\n[2] Registrando entradas no caixa...");
            caixa.registrarEntrada(50.0, "Dinheiro");
            caixa.registrarEntrada(100.0, "PIX");
            System.out.println("Entradas registradas com sucesso!");

            // Teste 3: Registrar saídas no caixa
            System.out.println("\n[3] Registrando saídas no caixa...");
            caixa.registrarSaida(30.0, "Dinheiro");
            System.out.println("Saídas registradas com sucesso!");

            // Teste 4: Consultar caixa aberto
            System.out.println("\n[4] Consultando caixa aberto...");
            Caixa caixaAberto = caixaService.obterCaixaAberto();
            if (caixaAberto != null) {
                System.out.println("Caixa aberto encontrado:");
                System.out.println("ID: " + caixaAberto.getId());
                System.out.println("Saldo Inicial: R$ " + caixaAberto.getSaldoInicial());
            } else {
                System.out.println("Nenhum caixa aberto no momento.");
            }

            // Teste 5: Fechar o caixa
            System.out.println("\n[5] Fechando o caixa...");
            caixa.setSaldoFinal(caixa.getSaldoInicial() + 50.0 - 30.0); // Calculando saldo final
            caixaService.fecharCaixa(caixa.getId(), caixa.getSaldoFinal());
            System.out.println("Caixa fechado com sucesso!");
            System.out.println("Saldo Final: R$ " + caixa.getSaldoFinal());

        } catch (SQLException e) {
            System.err.println("Erro ao executar o teste: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
