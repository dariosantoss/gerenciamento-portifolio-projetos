package br.com.projetos.controller;

import br.com.projetos.model.Membro;
import br.com.projetos.repository.MembroRepository;
import br.com.projetos.repository.PessoaRepository;
import br.com.projetos.repository.ProjetoRepository;
import br.com.projetos.service.MembroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MembroController.class)
public class MembroControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PessoaRepository pessoaRepository;

    @MockBean
    private ProjetoRepository projetoRepository;

    @MockBean
    private MembroRepository membroRepository;

    @MockBean
    private MembroService membroService;

    @Test
    public void testListarMembros() throws Exception {
        List<Membro> membros = List.of(new Membro()); // Assuming Membro has a no-args constructor
        when(membroRepository.findAll()).thenReturn(membros);

        mockMvc.perform(get("/membro/listar"))
                .andExpect(status().isOk())
                .andExpect(view().name("listaMembros"));
    }

    @Test
    public void testListarMembro() throws Exception {
        when(membroRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/membro/listar"))
                .andExpect(status().isOk())
                .andExpect(view().name("listaMembros"))
                .andExpect(model().attribute("membros", hasSize(0)));
    }

    @Test
    public void testMostrarFormularioAdicionarMembro() throws Exception {
        when(pessoaRepository.findAll()).thenReturn(Collections.emptyList());
        when(projetoRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/membro/adicionar/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("formularioAdicionarMembro"))
                .andExpect(model().attributeExists("membro"))
                .andExpect(model().attributeExists("pessoas"))
                .andExpect(model().attributeExists("projetos"));
    }

    @Test
    public void testAdicionarMembro() throws Exception {
        Membro membro = new Membro();
        mockMvc.perform(post("/membro/adicionar")
                        .flashAttr("membro", membro))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/membro/listar"));

        verify(membroService).associarPessoa(any(Membro.class));
    }
}