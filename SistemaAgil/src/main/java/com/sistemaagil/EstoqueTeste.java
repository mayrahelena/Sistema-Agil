package com.sistemaagil;
import com.sistemaagil.model.Estoque;  // Alterado para Estoque
import com.sistemaagil.service.EstoqueService;  // Alterado para EstoqueService
import java.sql.SQLException;

public class EstoqueTeste {
    public static void main(String[] args) {
        EstoqueService estoqueService = new EstoqueService();  // Alterado para EstoqueService

        try {
            // Teste 1: Abrir o estoque
            System.out.println("Abrindo o estoque...");
            Estoque estoqueAberto = estoqueService.abrirEstoque(100.00);  // Alterado para abrirEstoque
            System.out.println("Estoque aberto com ID: " + estoqueAberto.getId());

            // Teste 2: Buscar o estoque aberto
            System.out.println("Buscando o estoque aberto...");
            Estoque estoqueAtual = estoqueService.obterEstoqueAberto();  // Alterado para obterEstoqueAberto
            if (estoqueAtual != null) {
                System.out.println("Estoque encontrado: ID " + estoqueAtual.getId() +
                        ", Saldo Inicial: " + estoqueAtual.getSaldoInicial());
            } else {
                System.out.println("Nenhum estoque aberto.");
            }

            // Teste 3: Fechar o estoque
            System.out.println("Fechando o estoque...");
            estoqueService.fecharEstoque(estoqueAtual.getId(), 150.00);  // Alterado para fecharEstoque
            System.out.println("Estoque fechado com sucesso.");

        } catch (IllegalStateException e) {
            System.err.println("Erro no estado do sistema: " + e.getMessage());
        }
    }
}
