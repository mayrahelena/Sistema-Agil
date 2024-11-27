package com.sistemaagil;

import com.sistemaagil.service.EstoqueService;
import java.sql.Date;
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
                    estoqueService.gerarRelatorioEstoque();
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
        System.out.print("Nome do Estoque: ");
        String nome = scanner.nextLine();

        double preco;
        while (true) {
            try {
                System.out.print("Preço: ");
                preco = Double.parseDouble(scanner.nextLine().replace(",", "."));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número válido para o preço.");
            }
        }

        double precoCusto;
        while (true) {
            try {
                System.out.print("Preço de Custo: ");
                precoCusto = Double.parseDouble(scanner.nextLine().replace(",", "."));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número válido para o preço.");
            }
        }

        double margemLucro;
        while (true) {
            try {
                System.out.print("Margem de Lucro: ");
                margemLucro = Double.parseDouble(scanner.nextLine().replace(",", "."));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número válido para a margem.");
            }
        }

        Date validade;
        while (true) {
            try {
                System.out.print("Data de Validade (YYYY-MM-DD): ");
                String dataValidade = scanner.nextLine();
                validade = Date.valueOf(dataValidade);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Formato de data inválido! Por favor, use o formato YYYY-MM-DD.");
            }
        }

        int quantidade;
        while (true) {
            try {
                System.out.print("Quantidade: ");
                quantidade = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número válido para a quantidade.");
            }
        }

        estoqueService.adicionarEstoque(nome, preco, precoCusto, margemLucro, validade, quantidade);
    }

    private static void removerEstoque(Scanner scanner, EstoqueService estoqueService) {
        System.out.print("ID do Estoque a ser removido: ");
        int idEstoque = scanner.nextInt();
        estoqueService.removerEstoque(idEstoque);
    }

    private static void registrarEntrada(Scanner scanner, EstoqueService estoqueService) {
        System.out.print("ID do Estoque: ");
        int idEstoque = scanner.nextInt();
        System.out.print("Quantidade a ser adicionada: ");
        int quantidade = scanner.nextInt();

        estoqueService.registrarEntrada(idEstoque, quantidade);
    }

    private static void registrarSaida(Scanner scanner, EstoqueService estoqueService) {
        System.out.print("ID do Estoque: ");
        int idEstoque = scanner.nextInt();
        System.out.print("Quantidade a ser removida: ");
        int quantidade = scanner.nextInt();

        estoqueService.registrarSaida(idEstoque, quantidade);
    }
    
    
}