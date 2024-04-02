package br.com.projetos.service.impl;

import br.com.projetos.dto.ProjetoFiltroDTO;
import br.com.projetos.enums.StatusAnalise;
import br.com.projetos.repository.ProjetoRepository;
import br.com.projetos.service.ProjetoService;
import br.com.projetos.service.ProjetoSpecification;
import br.com.projetos.model.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoServiceImpl(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @Override
    public List<Projeto> listarProjetos() {
        return projetoRepository.findAll();
    }

    @Override
    public Optional<Projeto> encontrarPorId(Long id) {
        return projetoRepository.findById(id);
    }

    @Override
    public Projeto salvarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    @Override
    public void deletarProjeto(Long id) {
        Optional<Projeto> projeto = encontrarPorId(id);
        if(verificarDeletar(projeto.get()) == true) {
            projetoRepository.deleteById(id);
        }
    }

    @Override
    public Projeto editarProjeto(Long id, Projeto projetoEditar, ProjetoService projetoService){
        Projeto projeto = projetoService.encontrarPorId(id).get();
        projeto.setNome(projetoEditar.getNome());
        projeto.setDataInicio(projetoEditar.getDataInicio());
        projeto.setDataPrevisaoFim(projetoEditar.getDataPrevisaoFim());
        projeto.setDataFim(projetoEditar.getDataFim());
        projeto.setDescricao(projetoEditar.getDescricao());
        projeto.setStatus(projetoEditar.getStatus());
        projeto.setOrcamento(projetoEditar.getOrcamento());
        projeto.setRisco(projetoEditar.getRisco());
        projeto.setGerente(projetoEditar.getGerente());
        projetoRepository.save(projeto);
        return projeto;
    }

    @Override
    public List<Projeto> buscarProjetosPorNomeEStatus(ProjetoFiltroDTO filtroDTO) {
        Specification<Projeto> specification = ProjetoSpecification.buscarPorNomeEStatus(filtroDTO);
        return  projetoRepository.findAll(specification);
    }

    private Boolean verificarDeletar(Projeto projeto){
        if(projeto.getStatus().equals(StatusAnalise.INICIADO.getDescricao())
                || projeto.getStatus().equals(StatusAnalise.EM_ANDAMENTO.getDescricao())
                || projeto.getStatus().equals(StatusAnalise.ENCERRADO.getDescricao())
        ){
            return false;
        }
        return true;
    }
}
