package com.sistemaagil.service;

import com.sistemaagil.dao.RelatorioDAO;
import com.sistemaagil.model.Relatorio;

import java.util.List;

public class RelatorioService {
    private final RelatorioDAO relatorioDAO;

    public RelatorioService() {
        this.relatorioDAO = new RelatorioDAO();
    }

    public void gerarRelatorio(String conteudo) {
        Relatorio relatorio = new Relatorio();
        relatorio.setConteudo(conteudo);
        relatorioDAO.salvarRelatorio(relatorio);
    }

    public List<Relatorio> listarRelatorios() {
        return relatorioDAO.listarRelatorios();
    }
}