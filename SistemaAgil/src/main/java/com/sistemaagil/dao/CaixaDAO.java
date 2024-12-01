package com.sistemaagil.dao;

import com.sistemaagil.model.Caixa;

import java.sql.*;

public class CaixaDAO {

    // Método para buscar o caixa aberto
    public Caixa buscarCaixaAberto(Connection connection) throws SQLException {
        String sql = "SELECT * FROM Caixa WHERE DataFechamento IS NULL ORDER BY DataAbertura DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Caixa caixa = new Caixa();
                caixa.setId(rs.getInt("Caixa_id")); // Nome correto da coluna
                caixa.setAbertura(rs.getTimestamp("DataAbertura").toLocalDateTime());
                caixa.setSaldoInicial(rs.getDouble("SaldoInicial"));
                caixa.setLimiteCaixa(rs.getDouble("LimiteCaixa"));
                caixa.setStatus(rs.getString("Status"));
                caixa.setIdFuncionario(rs.getInt("Id_Funcionario"));
                return caixa;
            }
            return null; // Nenhum caixa aberto
        }
    }

    // Método para abrir um novo caixa
    public void abrirCaixa(Caixa caixa, Connection connection) throws SQLException {
        String sql = "INSERT INTO Caixa (DataAbertura, SaldoInicial, Status, LimiteCaixa, Id_Funcionario) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, Timestamp.valueOf(caixa.getAbertura()));
            stmt.setDouble(2, caixa.getSaldoInicial());
            stmt.setString(3, caixa.getStatus());
            stmt.setDouble(4, caixa.getLimiteCaixa());
            stmt.setInt(5, caixa.getIdFuncionario());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                caixa.setId(rs.getInt(1)); // Atribui o ID gerado ao objeto caixa
            }
        }
    }

    // Método para fechar um caixa
    public void fecharCaixa(Caixa caixa, Connection connection) throws SQLException {
        String sql = "UPDATE Caixa SET DataFechamento = ?, SaldoFinal = ? WHERE Caixa_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(caixa.getFechamento()));
            stmt.setDouble(2, caixa.getSaldoFinal());
            stmt.setInt(3, caixa.getId());
            stmt.executeUpdate();
        }
    }
}

