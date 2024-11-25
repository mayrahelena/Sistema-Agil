package com.sistemaagil;

import com.sistemaagil.util.ConexaoBD;
import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {

    public static void main(String[] args) {
        // Chama o método para obter a conexão com o banco de dados
        Connection conn = ConexaoBD.getConnection();
        
        // Verifica se a conexão foi bem-sucedida
        if (conn != null) {
            System.out.println("Conexão com o banco de dados foi realizada com sucesso!");
            try {
                // Fecha a conexão após o teste
                conn.close();
            } catch (SQLException e) {
                // Caso ocorra erro ao fechar a conexão, imprime a mensagem
                System.out.println("Erro ao tentar fechar a conexão: " + e.getMessage());
            }
        } else {
            // Caso a conexão falhe, exibe a mensagem de erro
            System.out.println("Falha ao conectar com o banco de dados.");
        }
    }
}