package br.com.projetos.service.impl;

import br.com.projetos.model.Membro;
import br.com.projetos.model.Pessoa;
import br.com.projetos.repository.MembroRepository;
import br.com.projetos.repository.PessoaRepository;
import br.com.projetos.service.MembroService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MembroServiceImpl implements MembroService {

    private final MembroRepository membroRepository;
    private final PessoaRepository pessoaRepository;

    public MembroServiceImpl(MembroRepository membroRepository, PessoaRepository pessoaRepository) {
        this.membroRepository = membroRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public void associarPessoa(Membro membro){
        Optional<Pessoa> pessoa = pessoaRepository.findById(membro.getPessoa().getId());
        if(pessoa.isPresent() && pessoa.get().isFuncionario()){
            membroRepository.save(membro);
        }
    }
}
