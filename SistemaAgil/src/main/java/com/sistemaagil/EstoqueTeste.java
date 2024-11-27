package com.sistemaagil;

import com.sistemaagil.model.Estoque;
import com.sistemaagil.service.EstoqueService;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class EstoqueTeste {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EstoqueService estoqueService = new EstoqueService();

        try {
            System.out.println("=== Teste do Módulo de Estoque ===");
            
            Estoque novoProduto = new Estoque("Produto A", 10.00, 5.00, 50.0, new Date(), 100, 10, "1234567890123");
            estoqueService.adicionarEstoque(novoProduto); // Adiciona um novo produto ao estoque
            System.out.println("Produto adicionado ao estoque: " + novoProduto.getNome());

            // Registrar entrada no estoque
            estoqueService.registrarEntrada(novoProduto.getId(), 20); // Certifique-se de que o ID está definido após a inserção
            System.out.println("Entrada registrada no estoque para o produto: " + novoProduto.getNome());

            // Registrar saída do estoque
            estoqueService.registrarSaida(novoProduto.getId(), 10); 
            System.out.println("Saída registrada no estoque para o produto: " + novoProduto.getNome());

            // Gerar relatório do estoque
            estoqueService.gerarRelatorioEstoque(); 

        } catch (SQLException e) {
            System.err.println("Erro durante os testes do estoque: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Erro no estado do sistema: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}