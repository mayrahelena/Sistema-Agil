package com.sistemaagil.service;

import com.sistemaagil.dao.RelatorioDAO;
import com.sistemaagil.model.Relatorio;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RelatorioService {
    private final RelatorioDAO relatorioDAO;
    private final Connection connection;

    // Consultas SQL como constantes para facilitar a manutenção
    private static final String SQL_VERIFICAR_RELATORIO = 
            "SELECT COUNT(*) FROM Relatorio WHERE Especificacoes = ? AND Tipo = ?";
    private static final String SQL_SALDO_CAIXA = 
            "SELECT SaldoInicial, SaldoFinal FROM Caixa WHERE Status = 'Fechado' ORDER BY DataFechamento DESC LIMIT 1";
    private static final String SQL_ESTOQUE = 
            "SELECT Nome_Produto, Estoque, Estoque_Minimo FROM Produto";
    private static final String SQL_LISTAR_RELATORIOS_POR_TIPO = 
            "SELECT * FROM Relatorio WHERE Tipo = ?";

    public RelatorioService(RelatorioDAO relatorioDAO, Connection connection) {
        this.relatorioDAO = relatorioDAO;
        this.connection = connection;
    }

    public void gerarRelatorio(String especificacoes, String tipo, LocalDateTime data) throws SQLException {
        Relatorio relatorio = new Relatorio(especificacoes, tipo, data);
        relatorioDAO.salvarRelatorio(relatorio);
    }

    public boolean verificarRelatorioExistente(String especificacoes, String tipo) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_VERIFICAR_RELATORIO)) {
            stmt.setString(1, especificacoes);
            stmt.setString(2, tipo);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public void gerarRelatorioSeguindoVerificacao(String especificacoes, String tipo, LocalDateTime data) throws SQLException {
        if (!verificarRelatorioExistente(especificacoes, tipo)) {
            gerarRelatorio(especificacoes, tipo, data);
        } else {
            System.out.println("Relatório já existe: " + especificacoes);
        }
    }

    // Método para consultar saldo de caixa
    private String consultarSaldoCaixa() throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SALDO_CAIXA);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                double saldoInicial = rs.getDouble("SaldoInicial");
                double saldoFinal = rs.getDouble("SaldoFinal");
                return String.format("Saldo Inicial: R$ %.2f\nSaldo Final: R$ %.2f\n", saldoInicial, saldoFinal);
            }
        }
        return "Saldo de caixa não encontrado.\n";
    }

    public void gerarRelatorioCaixa(String especificacoes) throws SQLException {
        especificacoes += "----------------------------------------------------\n";
        especificacoes += consultarSaldoCaixa();
        especificacoes += "----------------------------------------------------\n";
        gerarRelatorio(especificacoes, Relatorio.TIPO_CAIXA, LocalDateTime.now());
    }

    public void gerarRelatorioEstoque() throws SQLException {
        String especificacoes = "Relatório de Estoque Atualizado:\n";
        especificacoes += "----------------------------------------------------\n";

        try (PreparedStatement stmt = connection.prepareStatement(SQL_ESTOQUE);
             ResultSet rs = stmt.executeQuery()) {

            boolean encontrouProdutos = false;
            while (rs.next()) {
                especificacoes += String.format("%s - Estoque: %d - Estoque Mínimo: %d\n",
                        rs.getString("Nome_Produto"), rs.getInt("Estoque"), rs.getInt("Estoque_Minimo"));
                encontrouProdutos = true;
            }

            if (!encontrouProdutos) {
                especificacoes += "Nenhum produto encontrado no estoque.\n";
            }
        }

        especificacoes += "----------------------------------------------------\n";
        gerarRelatorio(especificacoes, Relatorio.TIPO_ESTOQUE, LocalDateTime.now());
    }

    // Método para listar relatórios por tipo, agora usando Optional
    public Optional<List<Relatorio>> listarRelatoriosPorTipo(String tipo) throws SQLException {
        List<Relatorio> relatorios = new ArrayList<>();
        
        try (PreparedStatement stmt = connection.prepareStatement(SQL_LISTAR_RELATORIOS_POR_TIPO)) {
            stmt.setString(1, tipo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    relatorios.add(new Relatorio(rs.getString("Especificacoes"), rs.getString("Tipo"), rs.getTimestamp("Data").toLocalDateTime()));
                }
            }
        }

        return relatorios.isEmpty() ? Optional.empty() : Optional.of(relatorios);
    }
}
