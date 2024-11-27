package com.sistemaagil.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    
    // Método para criar e retornar a conexão com o banco de dados
    public static Connection getConnection() {
        // Informações de conexão
        String url = "jdbc:mysql://localhost:3306/sistema_agil"; 
        String usuario = "root"; 
        String senha = "021998@Amor";

        try {
            // Retorna a conexão com o banco de dados
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            // Em caso de erro, exibe a mensagem de erro no console
            System.out.println("Erro ao tentar conectar ao banco de dados: " + e.getMessage());
            return null; // Retorna null se a conexão falhar
        }
    }
}