package com.sistemaagil.service;

import com.sistemaagil.dao.EstoqueDAO;
import com.sistemaagil.model.Estoque;

import java.sql.SQLException;

public class EstoqueService {
    private final EstoqueDAO estoqueDAO;

    public EstoqueService() {
        this.estoqueDAO = new EstoqueDAO();
    }

    // Adiciona um novo produto ao estoque
    public void adicionarEstoque(Estoque produto) throws SQLException {
        estoqueDAO.adicionarProduto(produto);
    }

    // Registra a entrada de um produto no estoque
    public void registrarEntrada(int idProduto, int quantidade) throws SQLException {
        estoqueDAO.registrarEntrada(idProduto, quantidade);
    }

    // Registra a saída de um produto do estoque
    public void registrarSaida(int idProduto, int quantidade) throws SQLException {
        estoqueDAO.registrarSaida(idProduto, quantidade);
    }

    // Gera um relatório do estoque
    public void gerarRelatorioEstoque() throws SQLException {
        estoqueDAO.gerarRelatorioEstoque();
    }

    // Remove um produto do estoque
    public void removerEstoque(int idProduto) throws SQLException {
        estoqueDAO.removerProduto(idProduto);
    }
}