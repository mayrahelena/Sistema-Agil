package com.sistemaagil.dao;

import com.sistemaagil.model.Relatorio;
import com.sistemaagil.util.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {

    public void salvarRelatorio(Relatorio relatorio) {
        String sql = "INSERT INTO relatorios (conteudo, data_geracao) VALUES (?, NOW())";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, relatorio.getConteudo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar relatório: " + e.getMessage());
        }
    }

    public List<Relatorio> listarRelatorios() {
        List<Relatorio> relatorios = new ArrayList<>();
        String sql = "SELECT * FROM relatorios";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Relatorio relatorio = new Relatorio();
                relatorio.setId(rs.getInt("id"));
                relatorio.setConteudo(rs.getString("conteudo"));
                relatorio.setDataGeracao(rs.getTimestamp("data_geracao"));
                relatorios.add(relatorio);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar relatórios: " + e.getMessage());
        }
        return relatorios;
    }
}