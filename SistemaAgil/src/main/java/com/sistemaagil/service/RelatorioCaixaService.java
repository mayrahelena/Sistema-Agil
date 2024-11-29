package com.sistemaagil.service;


import com.sistemaagil.model.RelatorioMovimentacaoCaixa;
import com.sistemaagil.util.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioCaixaService {

    public List<RelatorioMovimentacaoCaixa> gerarRelatorioMovimentacaoCaixa() {
        List<RelatorioMovimentacaoCaixa> movimentacoes = new ArrayList<>();
        String sql = "SELECT TipoDeEvento, Descricao, Data, " +
                "CASE WHEN TipoDeEvento = 'Entrada' THEN Valor ELSE 0 END AS Entrada, " +
                "CASE WHEN TipoDeEvento = 'Saída' THEN Valor ELSE 0 END AS Saida " +
                "FROM Registro WHERE TipoDeEvento IN ('Entrada', 'Saída') ORDER BY Data DESC";

        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String tipo = rs.getString("TipoDeEvento");
                String descricao = rs.getString("Descricao");
                Timestamp data = rs.getTimestamp("Data");
                double entrada = rs.getDouble("Entrada");
                double saida = rs.getDouble("Saida");

                RelatorioMovimentacaoCaixa relatorio = new RelatorioMovimentacaoCaixa(tipo, descricao, data, entrada, saida);
                movimentacoes.add(relatorio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movimentacoes;
    }
}
