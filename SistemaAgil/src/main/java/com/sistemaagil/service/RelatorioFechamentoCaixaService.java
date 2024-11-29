package com.sistemaagil.service;


import com.sistemaagil.model.RelatorioFechamentoCaixa;
import com.sistemaagil.util.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioFechamentoCaixaService {

    public List<RelatorioFechamentoCaixa> gerarRelatorioFechamentoCaixa() {
        List<RelatorioFechamentoCaixa> fechamentos = new ArrayList<>();
        String sql = "SELECT DataFechamento, SaldoInicial, SaldoFinal, Total_Pix, Total_Dinheiro, Total_Credito, Total_Debito " +
                "FROM Caixa WHERE Status = 'Fechado' ORDER BY DataFechamento DESC";

        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Timestamp dataFechamento = rs.getTimestamp("DataFechamento");
                double saldoInicial = rs.getDouble("SaldoInicial");
                double saldoFinal = rs.getDouble("SaldoFinal");
                double totalPix = rs.getDouble("Total_Pix");
                double totalDinheiro = rs.getDouble("Total_Dinheiro");
                double totalCredito = rs.getDouble("Total_Credito");
                double totalDebito = rs.getDouble("Total_Debito");

                RelatorioFechamentoCaixa relatorio = new RelatorioFechamentoCaixa(dataFechamento, saldoInicial, saldoFinal, totalPix, totalDinheiro, totalCredito, totalDebito);
                fechamentos.add(relatorio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fechamentos;
    }
}
