package com.sistemaagil.service;

import com.sistemaagil.dao.EstoqueDAO; // Importação da classe EstoqueDAO
import com.sistemaagil.model.Estoque;
import java.util.Date;

public class EstoqueService {
    private EstoqueDAO estoqueDAO;

    public EstoqueService() {
        this.estoqueDAO = new EstoqueDAO();
    }

    public void adicionarEstoque(String nome, double preco, double precoCusto, double margemLucro, Date validade, int quantidade) {
        estoqueDAO.adicionarEstoque(nome, preco, precoCusto, margemLucro, validade, quantidade);
    }

    public void removerEstoque(int idEstoque) {
        estoqueDAO.removerEstoque(idEstoque);
    }

    public void registrarEntrada(int idEstoque, int quantidade) {
        estoqueDAO.registrarEntrada(idEstoque, quantidade);
    }

    public void registrarSaida(int idEstoque, int quantidade) {
        estoqueDAO.registrarSaida(idEstoque, quantidade);
    }

    public void gerarRelatorioEstoque() {
        estoqueDAO.relatorioEstoque();
    }

    public Estoque abrirEstoque(double d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Estoque obterEstoqueAberto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void fecharEstoque(int id, double d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}