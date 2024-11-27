package com.sistemaagil.dao;

import com.sistemaagil.model.Caixa;

import java.sql.*;

public class CaixaDAO {

    // Método para obter uma conexão com o banco de dados
    public Connection getConnection() throws SQLException {
        // Ajuste conforme sua configuração do banco de dados
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/seu_banco", "usuario", "senha");
    }

    // Método para buscar o caixa aberto
    public Caixa buscarCaixaAberto(Connection connection) throws SQLException {
        String sql = "SELECT * FROM caixa WHERE fechamento IS NULL ORDER BY abertura DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Caixa caixa = new Caixa();
                caixa.setId(rs.getInt("id"));
                caixa.setAbertura(rs.getTimestamp("abertura").toLocalDateTime());
                caixa.setSaldoInicial(rs.getDouble("saldo_inicial"));
                return caixa;
            }
            return null;  // Nenhum caixa aberto
        }
    }

    // Método para abrir um novo caixa
    public void abrirCaixa(Caixa caixa, Connection connection) throws SQLException {
        String sql = "INSERT INTO caixa (abertura, saldo_inicial) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, Timestamp.valueOf(caixa.getAbertura()));
            stmt.setDouble(2, caixa.getSaldoInicial());
            stmt.executeUpdate();

            // Obtém o ID do caixa gerado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                caixa.setId(rs.getInt(1));  // Atribui o ID gerado ao caixa
            }
        }
    }

    // Método para fechar um caixa
    public void fecharCaixa(Caixa caixa, Connection connection) throws SQLException {
        String sql = "UPDATE caixa SET fechamento = ?, saldo_final = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(caixa.getFechamento()));
            stmt.setDouble(2, caixa.getSaldoFinal());
            stmt.setInt(3, caixa.getId());
            stmt.executeUpdate();
        }
    }
}

