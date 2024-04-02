package br.com.projetos.controller;

import br.com.projetos.dto.ProjetoFiltroDTO;
import br.com.projetos.model.Projeto;
import br.com.projetos.service.PessoaService;
import br.com.projetos.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
public class ProjetoController {

    private final ProjetoService projetoService;
    private final PessoaService pessoaService;

    @Autowired
    public ProjetoController(ProjetoService projetoService, PessoaService pessoaService) {
        this.projetoService = projetoService;
        this.pessoaService = pessoaService;
    }

    @GetMapping({"/","/projetos"})
    public String listarProjetos(Model model) {
        model.addAttribute("projetos", projetoService.listarProjetos());
        return "listaProjetos";
    }

    @GetMapping("/projetos/novo")
    public String exibirFormularioNovoProjeto(Model model) {
        model.addAttribute("projeto", new Projeto());
        model.addAttribute("gerente", pessoaService.buscarGerentes());
        return "formularioNovoProjeto";
    }

    @GetMapping("/buscar-projetos")
    public String buscarProjetos(@ModelAttribute ProjetoFiltroDTO filtroDTO, Model model
    ) {
        List<Projeto> projeto = projetoService.buscarProjetosPorNomeEStatus(filtroDTO);
        model.addAttribute("projetos", projeto);
        return "listaProjetos";
    }

    @PostMapping("/projetos")
    public String salvarProjeto(@ModelAttribute Projeto projeto) {
        projetoService.salvarProjeto(projeto);
        return "redirect:/projetos";
    }

    @PostMapping("/projetos/{id}/editar")
    public String exibirFormularioEditarProjeto(@PathVariable Long id, Model model) {
        Optional<Projeto> projeto = projetoService.encontrarPorId(id);
        if (projeto.isPresent()) {
            model.addAttribute("projeto", projeto.get());
            model.addAttribute("gerente", pessoaService.buscarGerentes());
            return "formularioEditarProjeto";
        } else {
            return "redirect:/projetos";
        }
    }

    @PostMapping("/projetos/{id}/editar/salvar")
    public String SalvarFormularioEditarProjeto(@PathVariable Long id, @ModelAttribute Projeto projetoEditar, Model model
    ) {
        Projeto projeto = projetoService.editarProjeto(id, projetoEditar, projetoService);
        model.addAttribute("projeto", projeto);
        model.addAttribute("gerente", pessoaService.buscarGerentes());
        return "formularioEditarProjeto";
    }

    @PostMapping("/projetos/{id}/deletar")
    public String deletarProjeto(@PathVariable Long id) {
        projetoService.deletarProjeto(id);
        return "redirect:/projetos";
    }


}