package com.sistemaagil.estoque;

import com.sistemaagil.dao.ProdutoDAO;

import java.util.Date;

public class EstoqueService {
    private ProdutoDAO produtoDAO;

    public EstoqueService() {
        this.produtoDAO = new ProdutoDAO();
    }

    public void adicionarProduto(String nome, double preco, double precoCusto, double margemLucro, Date validade, int quantidade) {
        produtoDAO.adicionarProduto(nome, preco, precoCusto, margemLucro, validade, quantidade);
    }

    public void removerProduto(int idProduto) {
        produtoDAO.removerProduto(idProduto);
    }

    public void registrarEntrada(int idProduto, int quantidade) {
        produtoDAO.registrarEntrada(idProduto, quantidade);
    }

    public void registrarSaida(int idProduto, int quantidade) {
        produtoDAO.registrarSaida(idProduto, quantidade);
    }

    public void gerarRelatorioEstoque() {
        produtoDAO.relatorioEstoque();
    }
}
