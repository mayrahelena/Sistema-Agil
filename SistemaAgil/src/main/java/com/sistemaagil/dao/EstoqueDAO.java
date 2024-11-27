package com.sistemaagil.dao;

import com.sistemaagil.model.Estoque;
import com.sistemaagil.util.ConexaoBD;

import java.sql.*;
import java.util.Date;

public class EstoqueDAO {
    private Connection connection;

    public EstoqueDAO() {
        this.connection = ConexaoBD.getConnection();
    }

    // Adicionar Produto no Estoque
    public void adicionarProduto(Estoque estoque) {
        String sql = "INSERT INTO produtos (nome, preco, preco_custo, margem_lucro, validade, quantidade, quantidade_minima, codigo_barras) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, estoque.getNome());
            stmt.setDouble(2, estoque.getPreco());
            stmt.setDouble(3, estoque.getPrecoCusto());
            stmt.setDouble(4, estoque.getMargemLucro());
            stmt.setDate(5, new java.sql.Date(estoque.getValidade().getTime()));
            stmt.setInt(6, estoque.getQuantidade());
            stmt.setInt(7, estoque.getQuantidadeMinima());
            stmt.setString(8, estoque.getCodigoBarras());
            stmt.executeUpdate();
            System.out.println("Produto adicionado ao estoque com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    // Remover Produto do Estoque
    public void removerProduto(int idProduto) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto removido do estoque com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
        }
    }

    // Registrar Entrada de Produto no Estoque
    public void registrarEntrada(int idProduto, int quantidade) {
        String sql = "UPDATE produtos SET quantidade = quantidade + ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setInt(2, idProduto);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Entrada registrada no estoque com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao registrar entrada: " + e.getMessage());
        }
    }

    // Registrar Saída de Produto do Estoque
    public void registrarSaida(int idProduto, int quantidade) {
        String sql = "UPDATE produtos SET quantidade = quantidade - ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setInt(2, idProduto);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Saída registrada do estoque com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao registrar saída: " + e.getMessage());
        }
    }

    // Gerar Relatório de Estoque
    public void gerarRelatorioEstoque() {
        String sql = "SELECT id, nome, quantidade, quantidade_minima FROM produtos";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("Relatório de Estoque:");
            while (rs.next()) {
                int idProduto = rs.getInt("id");
                String nomeProduto = rs.getString("nome");
                int quantidade = rs.getInt("quantidade");
                int quantidadeMinima = rs.getInt("quantidade_minima");

                System.out.printf("ID: %d | Produto: %s | Quantidade: %d | Quantidade Mínima: %d%n", 
                        idProduto, nomeProduto, quantidade, quantidadeMinima);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao gerar relatório de estoque: " + e.getMessage());
        }
    }

    // Verificar Estoque Mínimo
    public void verificarEstoqueMinimo() {
        String sql = "SELECT id, nome, quantidade, quantidade_minima FROM produtos WHERE quantidade <= quantidade_minima";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("Produtos com Estoque Mínimo:");
            while (rs.next()) {
                int idProduto = rs.getInt("id");
                String nomeProduto = rs.getString("nome");
                int quantidade = rs.getInt("quantidade");
                int quantidadeMinima = rs.getInt("quantidade_minima");

                System.out.printf("ID: %d | Produto: %s | Quantidade: %d | Quantidade Mínima: %d%n", 
                        idProduto, nomeProduto, quantidade, quantidadeMinima);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar estoque mínimo: " + e.getMessage());
        }
    }
}