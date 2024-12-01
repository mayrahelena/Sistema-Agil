package com.sistemaagil;

import com.sistemaagil.dao.RelatorioDAO;
import com.sistemaagil.model.Relatorio;
import com.sistemaagil.model.RelatorioFechamentoCaixa;
import com.sistemaagil.model.RelatorioMovimentacaoCaixa;
import com.sistemaagil.service.RelatorioService;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RelatorioTest {

    private static final int RELATORIO_CAIXA = 1;
    private static final int RELATORIO_ESTOQUE = 2;
    private static final int RELATORIO_FECHAMENTO_CAIXA = 3;
    private static final int RELATORIO_MOVIMENTACAO_CAIXA = 4;

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_agil", "root", "Senac@2024")) {
            RelatorioDAO relatorioDAO = new RelatorioDAO(connection);
            RelatorioService relatorioService = new RelatorioService(relatorioDAO, connection);

            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Escolha o tipo de relatório:");
                System.out.println(RELATORIO_CAIXA + " - Relatório de Caixa");
                System.out.println(RELATORIO_ESTOQUE + " - Relatório de Estoque");
                System.out.println(RELATORIO_FECHAMENTO_CAIXA + " - Relatório de Fechamento de Caixa");
                System.out.println(RELATORIO_MOVIMENTACAO_CAIXA + " - Relatório de Movimentação de Caixa");

                int opcao = lerOpcao(scanner);

                switch (opcao) {
                    case RELATORIO_CAIXA:
                        relatorioService.gerarRelatorioCaixa("Relatório de Caixa Atualizado");
                        exibirRelatorios(relatorioService, Relatorio.TIPO_CAIXA);
                        break;
                    case RELATORIO_ESTOQUE:
                        relatorioService.gerarRelatorioEstoque();
                        exibirRelatorios(relatorioService, Relatorio.TIPO_ESTOQUE);
                        break;
                    case RELATORIO_FECHAMENTO_CAIXA:
                        relatorioService.gerarRelatorioSeguindoVerificacao(
                                "Relatório de Fechamento de Caixa",
                                Relatorio.TIPO_FECHAMENTO_CAIXA,
                                obterDataAtual()
                        );
                        exibirRelatoriosFechamentoCaixa(connection);
                        break;
                    case RELATORIO_MOVIMENTACAO_CAIXA:
                        exibirRelatoriosMovimentacaoCaixa(connection);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    private static int lerOpcao(Scanner scanner) {
        int opcao = -1;
        while (opcao < RELATORIO_CAIXA || opcao > RELATORIO_MOVIMENTACAO_CAIXA) {
            System.out.print("Digite o número do relatório: ");
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                scanner.nextLine();
                opcao = -1;
            }
        }
        return opcao;
    }

    private static void exibirRelatorios(RelatorioService relatorioService, String tipoRelatorio) throws SQLException {
        Optional<List<Relatorio>> relatoriosOpt = relatorioService.listarRelatoriosPorTipo(tipoRelatorio);

        if (relatoriosOpt.isPresent()) {
            for (Relatorio relatorio : relatoriosOpt.get()) {
                System.out.println("Relatório: " + relatorio.getEspecificacoes());
            }
        } else {
            System.out.println("Nenhum relatório encontrado para o tipo: " + tipoRelatorio);
        }
    }

    private static void exibirRelatoriosFechamentoCaixa(Connection connection) {
        String query = "SELECT * FROM relatorio_fechamento_caixa";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            List<RelatorioFechamentoCaixa> relatorios = new ArrayList<>();

            while (resultSet.next()) {
                Timestamp dataFechamento = resultSet.getTimestamp("data_fechamento");
                double saldoInicial = resultSet.getDouble("saldo_inicial");
                double saldoFinal = resultSet.getDouble("saldo_final");
                double totalPix = resultSet.getDouble("total_pix");
                double totalDinheiro = resultSet.getDouble("total_dinheiro");
                double totalCredito = resultSet.getDouble("total_credito");
                double totalDebito = resultSet.getDouble("total_debito");

                RelatorioFechamentoCaixa relatorio = new RelatorioFechamentoCaixa(
                        dataFechamento, saldoInicial, saldoFinal, totalPix, totalDinheiro, totalCredito, totalDebito
                );

                relatorios.add(relatorio);
            }

            if (relatorios.isEmpty()) {
                System.out.println("Nenhum relatório de fechamento de caixa encontrado.");
            } else {
                System.out.println("Relatórios de Fechamento de Caixa:");
                for (RelatorioFechamentoCaixa relatorio : relatorios) {
                    System.out.println(relatorio);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar relatórios de fechamento de caixa: " + e.getMessage());
        }
    }

    private static void exibirRelatoriosMovimentacaoCaixa(Connection connection) {
        String query = "SELECT * FROM relatorio_movimentacao_caixa";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            List<RelatorioMovimentacaoCaixa> relatorios = new ArrayList<>();

            while (resultSet.next()) {
                String tipoMovimentacao = resultSet.getString("tipo_movimentacao");
                String descricao = resultSet.getString("descricao");
                Timestamp dataMovimentacao = resultSet.getTimestamp("data_movimentacao");
                double entrada = resultSet.getDouble("entrada");
                double saida = resultSet.getDouble("saida");

                RelatorioMovimentacaoCaixa relatorio = new RelatorioMovimentacaoCaixa(
                        tipoMovimentacao, descricao, dataMovimentacao, entrada, saida
                );

                relatorios.add(relatorio);
            }

            if (relatorios.isEmpty()) {
                System.out.println("Nenhum relatório de movimentação de caixa encontrado.");
            } else {
                System.out.println("Relatórios de Movimentação de Caixa:");
                for (RelatorioMovimentacaoCaixa relatorio : relatorios) {
                    System.out.println(relatorio);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar relatórios de movimentação de caixa: " + e.getMessage());
        }
    }

    private static LocalDateTime obterDataAtual() {
        return LocalDateTime.now();
    }
}
