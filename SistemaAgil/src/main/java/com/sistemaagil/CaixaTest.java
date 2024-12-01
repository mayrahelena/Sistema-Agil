
package com.sistemaagil;

import com.sistemaagil.model.Caixa;
import com.sistemaagil.service.CaixaService;
import java.sql.SQLException;

public class CaixaTest {

    public static void main(String[] args) {
        CaixaService caixaService = new CaixaService();

        try {
            System.out.println("=== TESTE DO SISTEMA DE CAIXA ===");

            // Fechar caixa se houver um aberto
            Caixa caixaAberto = caixaService.obterCaixaAberto();
            if (caixaAberto != null) {
                System.out.println("Fechando caixa aberto...");
                double saldoFinal = caixaAberto.getSaldoInicial() + 0.0; // Exemplo de saldo final
                caixaService.fecharCaixa(caixaAberto.getId(), saldoFinal);
                System.out.println("Caixa fechado com sucesso!");
            }

            // Teste 1: Abrir um novo caixa
            System.out.println("\n[1] Abrindo novo caixa...");
            Caixa caixa = caixaService.abrirCaixa(100.0, 500.0, 1); // Saldo inicial, limite do caixa, ID do funcionário
            System.out.println("Caixa aberto com sucesso!");
            System.out.println("ID do Caixa: " + caixa.getId());
            System.out.println("Saldo Inicial: R$ " + caixa.getSaldoInicial());
            System.out.println("Limite do Caixa: R$ " + caixa.getLimiteCaixa());

            // Teste 2: Consultar caixa aberto
            System.out.println("\n[2] Consultando caixa aberto...");
            caixaAberto = caixaService.obterCaixaAberto();
            if (caixaAberto != null) {
                System.out.println("Caixa aberto encontrado:");
                System.out.println("ID: " + caixaAberto.getId());
                System.out.println("Saldo Inicial: R$ " + caixaAberto.getSaldoInicial());
                System.out.println("Limite do Caixa: R$ " + caixaAberto.getLimiteCaixa());
            } else {
                System.out.println("Nenhum caixa aberto no momento.");
            }

            // Teste 3: Fechar o caixa
            System.out.println("\n[3] Fechando o caixa...");
            double saldoFinal = caixa.getSaldoInicial() + 150.0 - 50.0; // Simulação de saldo final
            caixaService.fecharCaixa(caixa.getId(), saldoFinal);
            System.out.println("Caixa fechado com sucesso!");
            System.out.println("Saldo Final: R$ " + saldoFinal);

        } catch (SQLException e) {
            System.err.println("Erro ao executar o teste: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
