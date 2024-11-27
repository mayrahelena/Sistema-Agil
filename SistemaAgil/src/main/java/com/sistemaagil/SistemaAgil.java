package com.sistemaagil;

import com.sistemaagil.model.Estoque;
import com.sistemaagil.service.EstoqueService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class SistemaAgil {
    public static void main(String[] args) {
        EstoqueService estoqueService = new EstoqueService();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("=== Sistema de Gestão de Estoque ===");
            System.out.println("1. Adicionar Estoque");
            System.out.println("2. Remover Estoque");
            System.out.println("3. Registrar Entrada no Estoque");
            System.out.println("4. Registrar Saída do Estoque");
            System.out.println("5. Gerar Relatório de Estoque");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    adicionarEstoque(scanner, estoqueService);
                    break;
                case 2:
                    removerEstoque(scanner, estoqueService);
                    break;
                case 3:
                    registrarEntrada(scanner, estoqueService);
                    break;
                case 4:
                    registrarSaida(scanner, estoqueService);
                    break;
                case 5:
                    try {
                        estoqueService.gerarRelatorioEstoque();
                    } catch (SQLException e) {
                        System.err.println("Erro ao gerar relatório de estoque: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void adicionarEstoque(Scanner scanner, EstoqueService estoqueService) {
        scanner.nextLine(); // Limpar o buffer
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();

        double preco = obterDouble(scanner, "Preço: ");
        double precoCusto = obterDouble(scanner, "Preço de Custo: ");
        double margemLucro = obterDouble(scanner, "Margem de Lucro: ");
        
        Date validade = obterData(scanner, "Data de Validade (YYYY-MM-DD): ");
        
        int quantidade = obterInt(scanner, "Quantidade: ");

        // Criar o objeto Estoque
        Estoque novoProduto = new Estoque(nome, preco, precoCusto, margemLucro, validade, quantidade, 10, "1234567890123");

        try {
            estoqueService.adicionarEstoque(novoProduto); // Adiciona um novo produto ao estoque
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar produto ao estoque: " + e.getMessage());
        }
    }

    private static double obterDouble(Scanner scanner, String mensagem) {
        double valor;
        while (true) {
            try {
                System.out.print(mensagem);
                valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número válido.");
            }
        }
    }

    private static Date obterData(Scanner scanner, String mensagem) {
        Date data;
        while (true) {
            try {
                System.out.print(mensagem);
                String dataValidade = scanner.nextLine();
                data = Date.valueOf(dataValidade);
                return data;
            } catch (IllegalArgumentException e) {
                System.out.println("Formato de data inválido! Por favor, use o formato YYYY-MM-DD.");
            }
        }
    }

    private static int obterInt(Scanner scanner, String mensagem) {
        int valor;
        while (true) {
            try {
                System.out.print(mensagem);
                valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número válido.");
            }
        }
    }

    private static void removerEstoque(Scanner scanner, EstoqueService estoqueService) {
        System.out.print("ID do Estoque a ser removido: ");
        int idEstoque = scanner.nextInt();
        
        try {
            estoqueService.removerEstoque(idEstoque);
        } catch (SQLException e) {
            System.err.println("Erro ao remover produto do estoque: " + e.getMessage());
        }
    }

    private static void registrarEntrada(Scanner scanner, EstoqueService estoqueService) {
        System.out.print("ID do Estoque: ");
        int idEstoque = scanner.nextInt();
        
        System.out.print("Quantidade a ser adicionada: ");
        int quantidade = scanner.nextInt();

        try {
            estoqueService.registrarEntrada(idEstoque, quantidade);
        } catch (SQLException e) {
            System.err.println("Erro ao registrar entrada no estoque: " + e.getMessage());
        }
    }

    private static void registrarSaida(Scanner scanner, EstoqueService estoqueService) {
        System.out.print("ID do Estoque: ");
        int idEstoque = scanner.nextInt();
        
        System.out.print("Quantidade a ser removida: ");
        int quantidade = scanner.nextInt();

        try {
            estoqueService.registrarSaida(idEstoque, quantidade);
        } catch (SQLException e) {
            System.err.println("Erro ao registrar saída do estoque: " + e.getMessage());
        }
    }
}