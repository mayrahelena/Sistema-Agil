package com.sistemaagil;

import com.sistemaagil.model.Caixa;
import com.sistemaagil.service.CaixaService;

import java.sql.SQLException;

public class ProjetoCaixa {
    public static void main(String[] args) {
        CaixaService caixaService = new CaixaService();

        try {
            // Teste 1: Abrir o caixa
            System.out.println("Abrindo o caixa...");
            Caixa caixaAberto = caixaService.abrirCaixa(100.00);
            System.out.println("Caixa aberto com ID: " + caixaAberto.getId());

            // Teste 2: Buscar o caixa aberto
            System.out.println("Buscando o caixa aberto...");
            Caixa caixaAtual = caixaService.obterCaixaAberto();
            if (caixaAtual != null) {
                System.out.println("Caixa encontrado: ID " + caixaAtual.getId() +
                        ", Saldo Inicial: " + caixaAtual.getSaldoInicial());
            } else {
                System.out.println("Nenhum caixa aberto.");
            }

            // Teste 3: Fechar o caixa
            System.out.println("Fechando o caixa...");
            caixaService.fecharCaixa(caixaAtual.getId(), 150.00);
            System.out.println("Caixa fechado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao executar teste: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Erro no estado do sistema: " + e.getMessage());
        }
    }
}
