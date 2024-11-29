package com.sistemaagil.dao;

import com.sistemaagil.model.Relatorio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {
    private Connection connection;

    public RelatorioDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para salvar um novo relatório
    public void salvarRelatorio(Relatorio relatorio) throws SQLException {
        String sql = "INSERT INTO Relatorio (Especificacoes, Tipo, Data) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, relatorio.getEspecificacoes());
            stmt.setString(2, relatorio.getTipo());
            stmt.setTimestamp(3, new Timestamp(relatorio.getData().getTime()));
            stmt.executeUpdate();
        }
    }

    // Método para listar relatórios filtrados por tipo
    public List<Relatorio> listarRelatoriosPorTipo(String tipo) throws SQLException {
        List<Relatorio> relatorios = new ArrayList<>();
        String sql = "SELECT * FROM Relatorio WHERE Tipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Relatorio relatorio = new Relatorio(
                        rs.getString("Especificacoes"),
                        rs.getString("Tipo"),
                        rs.getTimestamp("Data")
                    );
                    relatorio.setId(rs.getInt("Relatorio_id"));
                    relatorios.add(relatorio);
                }
            }
        }
        return relatorios;
    }
}
