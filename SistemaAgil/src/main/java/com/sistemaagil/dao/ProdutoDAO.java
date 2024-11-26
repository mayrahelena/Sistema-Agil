package com.sistemaagil.dao;

import com.sistemaagil.util.ConexaoBD;

import java.sql.*;
import java.util.Date;

public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO() {
        this.connection = ConexaoBD.getConnection(); // Obtém a conexão usando a classe ConexaoBD
    }

   public void adicionarProduto(String nome, double preco, double precoCusto, double margemLucro, Date validade, int quantidade, boolean produtoVendido) {
    String sql = "INSERT INTO produtos (nome, preco, preco_custo, margem_lucro, validade, quantidade, produto_vendido) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, nome);
        stmt.setDouble(2, preco);
        stmt.setDouble(3, precoCusto);
        stmt.setDouble(4, margemLucro);
        stmt.setDate(5, new java.sql.Date(validade.getTime()));
        stmt.setInt(6, quantidade);
        stmt.setBoolean(7, produtoVendido);
        stmt.executeUpdate();
        System.out.println("Produto adicionado com sucesso!");
    } catch (SQLException e) {
        System.out.println("Erro ao adicionar produto: " + e.getMessage());
    }
}
   
   public void marcarComoVendido(int idProduto) {
    String sql = "UPDATE produtos SET produto_vendido = ? WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setBoolean(1, true);
        stmt.setInt(2, idProduto);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Produto marcado como vendido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao marcar produto como vendido: " + e.getMessage());
    }
}

    public void removerProduto(int idProduto) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
        }
    }

    public void registrarEntrada(int idProduto, int quantidade) {
        String sql = "UPDATE produtos SET quantidade = quantidade + ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setInt(2, idProduto);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Entrada registrada com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao registrar entrada: " + e.getMessage());
        }
    }

   public void registrarSaida(int idProduto, int quantidade) {
    String verificarSql = "SELECT produto_vendido FROM produtos WHERE id = ?";
    try (PreparedStatement verificarStmt = connection.prepareStatement(verificarSql)) {
        verificarStmt.setInt(1, idProduto);
        ResultSet rs = verificarStmt.executeQuery();
        if (rs.next() && rs.getBoolean("produto_vendido")) {
            System.out.println("Erro: Produto já foi vendido e não pode ser alterado.");
            return;
        }
    } catch (SQLException e) {
        System.out.println("Erro ao verificar status do produto: " + e.getMessage());
        return;
    }

    String atualizarSql = "UPDATE produtos SET quantidade = quantidade - ? WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(atualizarSql)) {
        stmt.setInt(1, quantidade);
        stmt.setInt(2, idProduto);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Saída registrada com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao registrar saída: " + e.getMessage());
    }
}

    public void relatorioEstoque() {
        String sql = "SELECT id, nome, quantidade FROM produtos";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Relatório de Estoque:");
            while (rs.next()) {
                int idProduto = rs.getInt("id");
                String nomeProduto = rs.getString("nome");
                int quantidade = rs.getInt("quantidade");
                System.out.printf("ID: %d | Produto: %s | Quantidade: %d%n", idProduto, nomeProduto, quantidade);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao gerar relatório de estoque: " + e.getMessage());
        }
    }

    public void adicionarProduto(String nome, double preco, double precoCusto, double margemLucro, Date validade, int quantidade) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
