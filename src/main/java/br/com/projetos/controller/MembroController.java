package br.com.projetos.controller;

import br.com.projetos.model.Membro;
import br.com.projetos.repository.MembroRepository;
import br.com.projetos.repository.PessoaRepository;
import br.com.projetos.repository.ProjetoRepository;
import br.com.projetos.service.MembroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/membro")
@Controller
public class MembroController {

    private final PessoaRepository pessoaRepository;
    private final ProjetoRepository projetoRepository;
    private final MembroRepository membroRepository;
    private final MembroService membroService;

    public MembroController(PessoaRepository pessoaRepository, ProjetoRepository projetoRepository,MembroRepository membroRepository, MembroService membroService) {
        this.pessoaRepository = pessoaRepository;
        this.projetoRepository = projetoRepository;
        this.membroRepository = membroRepository;
        this.membroService = membroService;
    }

    @GetMapping("/listar")
    public String listarMembros(Model model) {
        model.addAttribute("membros", membroRepository.findAll());
        return "listaMembros";
    }

    @GetMapping("/adicionar/form")
    public String mostrarFormularioAdicionarMembro(Model model) {
        model.addAttribute("membro", new Membro());
        model.addAttribute("pessoas", pessoaRepository.findAll());
        model.addAttribute("projetos", projetoRepository.findAll());
        return "formularioAdicionarMembro";
    }

    @PostMapping("/adicionar")
    public String adicionarMembro(@ModelAttribute Membro membro) {
        try {
            membroService.associarPessoa(membro);
        }catch (Exception e){
            System.out.println(e);
        }
        return "redirect:/membro/listar";
    }

}
