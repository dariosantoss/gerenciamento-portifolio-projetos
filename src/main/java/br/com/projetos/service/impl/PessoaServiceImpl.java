package br.com.projetos.service.impl;

import br.com.projetos.service.PessoaService;
import br.com.projetos.model.Pessoa;
import br.com.projetos.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public List<Pessoa> buscarGerentes() {
        return pessoaRepository.findByGerente(true);
    }

    @Override
    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
}
