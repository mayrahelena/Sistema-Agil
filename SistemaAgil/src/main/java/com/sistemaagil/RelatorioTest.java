package com.sistemaagil;

import com.sistemaagil.dao.RelatorioDAO;
import com.sistemaagil.model.Relatorio;
import com.sistemaagil.service.RelatorioService;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class RelatorioTest {
    public static void main(String[] args) throws SQLException {
        // Conexão com o banco (substitua com a sua conexão real)
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_agil", "root", "Senac@2024");

        // Criando o DAO e Service
        RelatorioDAO relatorioDAO = new RelatorioDAO(connection);
        RelatorioService relatorioService = new RelatorioService(relatorioDAO, connection);

        // Criando um Scanner para entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Exibindo as opções para o usuário escolher o tipo de relatório
        System.out.println("Escolha o tipo de relatório:");
        System.out.println("1 - Relatório de Caixa");
        System.out.println("2 - Relatório de Estoque");
        System.out.println("3 - Relatório de Fechamento de Caixa");

        // Lendo a opção escolhida
        int opcao = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer do scanner

        // Gerando o relatório com base na opção do usuário
        if (opcao == 1) {
            // Relatório de Caixa
            relatorioService.gerarRelatorioSeguindoVerificacao("Relatório de Caixa para o mês de novembro", Relatorio.TIPO_CAIXA, new java.util.Date());
        } else if (opcao == 2) {
            // Relatório de Estoque
            relatorioService.gerarRelatorioSeguindoVerificacao("Relatório de Estoque atualizado", Relatorio.TIPO_ESTOQUE, new java.util.Date());
        } else if (opcao == 3) {
            // Relatório de Fechamento de Caixa
            relatorioService.gerarRelatorioSeguindoVerificacao("Relatório de Fechamento de Caixa", Relatorio.TIPO_FECHAMENTO_CAIXA, new java.util.Date());
        } else {
            System.out.println("Opção inválida!");
            return;
        }

        // Listando os relatórios de acordo com a opção escolhida
        List<Relatorio> relatorios = relatorioService.listarRelatoriosPorTipo(
                opcao == 1 ? Relatorio.TIPO_CAIXA : (opcao == 2 ? Relatorio.TIPO_ESTOQUE : Relatorio.TIPO_FECHAMENTO_CAIXA)
        );
        for (Relatorio relatorio : relatorios) {
            System.out.println("Relatório: " + relatorio.getEspecificacoes());
        }

        // Fechando a conexão
        connection.close();
    }
}
