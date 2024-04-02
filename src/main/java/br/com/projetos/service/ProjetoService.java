package br.com.projetos.service;

import br.com.projetos.dto.ProjetoFiltroDTO;
import br.com.projetos.model.Projeto;

import java.util.List;
import java.util.Optional;

public interface ProjetoService {
    List<Projeto> listarProjetos();
    Optional<Projeto> encontrarPorId(Long id);
    Projeto salvarProjeto(Projeto projeto);
    void deletarProjeto(Long id);
    Projeto editarProjeto(Long id, Projeto projeto, ProjetoService projetoService);
    List<Projeto> buscarProjetosPorNomeEStatus(ProjetoFiltroDTO filtroDTO);
}


