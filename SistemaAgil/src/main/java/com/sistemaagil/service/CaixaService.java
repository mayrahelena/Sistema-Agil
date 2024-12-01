package com.sistemaagil.service;

import com.sistemaagil.dao.CaixaDAO;
import com.sistemaagil.model.Caixa;
import com.sistemaagil.util.ConexaoBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CaixaService {
    private CaixaDAO caixaDAO;

    public CaixaService() {
        this.caixaDAO = new CaixaDAO();
    }

    // Método para abrir um novo caixa com parâmetros adicionais
    public Caixa abrirCaixa(double saldoInicial, double limiteCaixa, int idFuncionario) throws SQLException {
        try (Connection connection = ConexaoBD.getConnection()) {
            connection.setAutoCommit(false); // Desabilita o commit automático

            // Verifica se já existe um caixa aberto
            Caixa caixaAberto = caixaDAO.buscarCaixaAberto(connection);
            if (caixaAberto != null) {
                connection.rollback(); // Desfaz a operação se o caixa já estiver aberto
                throw new IllegalStateException("Já existe um caixa aberto.");
            }

            // Cria um novo caixa
            Caixa caixa = new Caixa();
            caixa.setAbertura(LocalDateTime.now());
            caixa.setSaldoInicial(saldoInicial);
            caixa.setLimiteCaixa(limiteCaixa);
            caixa.setStatus("Aberto");
            caixa.setIdFuncionario(idFuncionario);

            // Persiste o novo caixa no banco de dados
            caixaDAO.abrirCaixa(caixa, connection);

            connection.commit(); // Comita a transação
            return caixa;
        } catch (SQLException e) {
            throw new SQLException("Erro ao abrir o caixa: " + e.getMessage(), e);
        }
    }

    // Método para fechar o caixa
    public void fecharCaixa(int caixaId, double saldoFinal) throws SQLException {
        try (Connection connection = ConexaoBD.getConnection()) {
            connection.setAutoCommit(false); // Desabilita o commit automático

            // Busca o caixa aberto
            Caixa caixa = caixaDAO.buscarCaixaAberto(connection);
            if (caixa != null && caixa.getId() == caixaId) {
                caixa.setFechamento(LocalDateTime.now());
                caixa.setSaldoFinal(saldoFinal);
                caixaDAO.fecharCaixa(caixa, connection);
                connection.commit(); // Comita a transação após fechar o caixa
            } else {
                connection.rollback(); // Desfaz a operação se não houver caixa aberto ou ID não corresponde
                throw new IllegalStateException("Nenhum caixa aberto para fechar.");
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao fechar o caixa: " + e.getMessage(), e);
        }
    }

    // Método para obter o caixa aberto
    public Caixa obterCaixaAberto() throws SQLException {
        try (Connection connection = ConexaoBD.getConnection()) {
            return caixaDAO.buscarCaixaAberto(connection);
        }
    }
}

