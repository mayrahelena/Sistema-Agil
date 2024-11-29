package com.sistemaagil.dao;

import com.sistemaagil.model.Caixa;

import java.sql.*;

public class CaixaDAO {

    // Método para obter uma conexão com o banco de dados
    public Connection getConnection() throws SQLException {
        // Ajuste conforme sua configuração do banco de dados
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_agil", "usuario", "senha");
    }

    // Método para buscar o caixa aberto
    public Caixa buscarCaixaAberto(Connection connection) throws SQLException {
        // Ajustado os nomes das colunas de acordo com o banco de dados
        String sql = "SELECT * FROM Caixa WHERE DataFechamento IS NULL ORDER BY DataAbertura DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Caixa caixa = new Caixa();
                caixa.setId(rs.getInt("Caixa_id")); // Nome correto da coluna
                caixa.setAbertura(rs.getTimestamp("DataAbertura").toLocalDateTime());
                caixa.setSaldoInicial(rs.getDouble("SaldoInicial"));
                return caixa;
            }
            return null;  // Nenhum caixa aberto
        }
    }

    // Método para abrir um novo caixa
    public void abrirCaixa(Caixa caixa, Connection connection) throws SQLException {
        // Ajustado os nomes das colunas de acordo com o banco de dados
        String sql = "INSERT INTO Caixa (DataAbertura, SaldoInicial, Status, LimiteCaixa, Id_Funcionario) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, Timestamp.valueOf(caixa.getAbertura()));
            stmt.setDouble(2, caixa.getSaldoInicial());
            stmt.setString(3, caixa.getStatus());
            stmt.setDouble(4, caixa.getLimiteCaixa());
            stmt.setInt(5, caixa.getIdFuncionario());
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
        // Ajustado os nomes das colunas de acordo com o banco de dados
        String sql = "UPDATE Caixa SET DataFechamento = ?, SaldoFinal = ? WHERE Caixa_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(caixa.getFechamento()));
            stmt.setDouble(2, caixa.getSaldoFinal());
            stmt.setInt(3, caixa.getId());
            stmt.executeUpdate();
        }
    }
}
