package com.sistemaagil.service;


import com.sistemaagil.model.RelatorioEstoque;
import com.sistemaagil.util.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RelatorioEstoqueService {

    public List<RelatorioEstoque> gerarRelatorioEstoque() {
        List<RelatorioEstoque> relatorios = new ArrayList<>();
        String sql = "SELECT Nome_Produto, Estoque, Estoque_Minimo FROM Produto WHERE Estoque <= Estoque_Minimo ORDER BY Nome_Produto";

        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nomeProduto = rs.getString("Nome_Produto");
                int quantidadeEstoque = rs.getInt("Estoque");
                int quantidadeMinima = rs.getInt("Estoque_Minimo");

                RelatorioEstoque relatorio = new RelatorioEstoque(nomeProduto, quantidadeEstoque, quantidadeMinima);
                relatorios.add(relatorio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relatorios;
    }
}

