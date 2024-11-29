package com.sistemaagil.service;

import com.sistemaagil.dao.RelatorioDAO;
import com.sistemaagil.model.Relatorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioService {
    private final RelatorioDAO relatorioDAO;
    private final Connection connection;

    public RelatorioService(RelatorioDAO relatorioDAO, Connection connection) {
        this.relatorioDAO = relatorioDAO;
        this.connection = connection;
    }

    // Método para gerar um relatório
    public void gerarRelatorio(String especificacoes, String tipo, java.util.Date data) throws SQLException {
        Relatorio relatorio = new Relatorio(especificacoes, tipo, data);
        relatorioDAO.salvarRelatorio(relatorio);
    }

    // Método para verificar se o relatório já existe no banco
    public boolean verificarRelatorioExistente(String especificacoes, String tipo) throws SQLException {
        String sqlVerificarRelatorio = "SELECT COUNT(*) FROM Relatorio WHERE Especificacoes = ? AND Tipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlVerificarRelatorio)) {
            stmt.setString(1, especificacoes);
            stmt.setString(2, tipo);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Se já existir, retorna true
                }
            }
        }
        return false;
    }

    // Método para gerar relatório de forma segura, verificando duplicidade
    public void gerarRelatorioSeguindoVerificacao(String especificacoes, String tipo, java.util.Date data) throws SQLException {
        if (!verificarRelatorioExistente(especificacoes, tipo)) {
            gerarRelatorio(especificacoes, tipo, data);
        } else {
            System.out.println("Relatório já existe: " + especificacoes);
        }
    }

    // Método para gerar relatório de Caixa com dados do banco
    public void gerarRelatorioCaixa(String especificacoes) throws SQLException {
        especificacoes += "----------------------------------------------------\n";

        // Consultar os dados de caixa no banco
        String sqlCaixa = "SELECT SaldoInicial, SaldoFinal FROM Caixa WHERE Status = 'Fechado' ORDER BY DataFechamento DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sqlCaixa);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                double saldoInicial = rs.getDouble("SaldoInicial");
                double saldoFinal = rs.getDouble("SaldoFinal");

                especificacoes += "Saldo Inicial: R$ " + saldoInicial + "\n"; // Dados do banco
                especificacoes += "Total Pix: R$ 0.00\n"; // Você pode adicionar a consulta para Pix se necessário
                especificacoes += "Total Dinheiro: R$ 0.00\n"; // Como acima, pode ser consultado conforme sua necessidade
                especificacoes += "Total Crédito: R$ 0.00\n"; // Adapte conforme a lógica que você tem para tipos de pagamento
                especificacoes += "Total Débito: R$ 0.00\n"; // Igualmente para o débito
                especificacoes += "Saldo Final: R$ " + saldoFinal + "\n"; // Dados do banco
            }
        }

        especificacoes += "----------------------------------------------------\n";

        // Gerar relatório de Caixa
        gerarRelatorio(especificacoes, Relatorio.TIPO_CAIXA, new java.util.Date());
    }

    // Método para gerar relatório de Estoque com dados do banco
    public void gerarRelatorioEstoque() throws SQLException {
        String especificacoes = "Relatório de Estoque Atualizado:\n";
        especificacoes += "----------------------------------------------------\n";

        // Consultar os produtos do banco de dados
        String sqlEstoque = "SELECT Nome_Produto, Estoque, Estoque_Minimo FROM Produto";
        try (PreparedStatement stmt = connection.prepareStatement(sqlEstoque);
             ResultSet rs = stmt.executeQuery()) {

            boolean encontrouProdutos = false;  // Flag para verificar se encontramos produtos

            while (rs.next()) {
                especificacoes += rs.getString("Nome_Produto") + " - Estoque: " + rs.getInt("Estoque") + 
                                  " - Estoque Mínimo: " + rs.getInt("Estoque_Minimo") + "\n";
                encontrouProdutos = true;  // Encontramos pelo menos um produto
            }

            // Se não encontrou produtos, informe no relatório
            if (!encontrouProdutos) {
                especificacoes += "Nenhum produto encontrado no estoque.\n";
            }
        }

        especificacoes += "----------------------------------------------------\n";

        // Gerar o relatório de Estoque
        gerarRelatorio(especificacoes, Relatorio.TIPO_ESTOQUE, new java.util.Date());
    }

    // Implementação do método listarRelatoriosPorTipo
    public List<Relatorio> listarRelatoriosPorTipo(String tipo) throws SQLException {
        List<Relatorio> relatorios = new ArrayList<>();
        String sql = "SELECT * FROM Relatorio WHERE Tipo = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String especificacoes = rs.getString("Especificacoes");
                    String tipoRelatorio = rs.getString("Tipo");
                    Date data = rs.getDate("Data");
                    Relatorio relatorio = new Relatorio(especificacoes, tipoRelatorio, data);
                    relatorios.add(relatorio);
                }
            }
        }
        return relatorios;
    }
}
