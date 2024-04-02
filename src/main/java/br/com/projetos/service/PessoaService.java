package br.com.projetos.service;

import br.com.projetos.model.Pessoa;

import java.util.List;

public interface PessoaService {
    List<Pessoa> buscarGerentes();
    Pessoa salvarPessoa(Pessoa essoa);
}
