package br.com.projetos.controller;

import br.com.projetos.model.Projeto;
import br.com.projetos.service.PessoaService;
import br.com.projetos.service.ProjetoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjetoController.class)
public class ProjetoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProjetoService projetoService;

    @MockBean
    private PessoaService pessoaService;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testListarProjetosWhenCalledThenReturnListOfProjects() throws Exception {
        given(projetoService.listarProjetos()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/projetos"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("projetos"))
                .andExpect(view().name("listaProjetos"));
    }

    @Test
    public void testExibirFormularioNovoProjeto() throws Exception {
        mockMvc.perform(get("/projetos/novo"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("projeto", "gerente"))
                .andExpect(view().name("formularioNovoProjeto"));
    }

    @Test
    public void testBuscarProjetos() throws Exception {
        given(projetoService.buscarProjetosPorNomeEStatus(null)).willReturn(Collections.emptyList());

        mockMvc.perform(get("/buscar-projetos"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("projetos"))
                .andExpect(view().name("listaProjetos"));
    }

    @Test
    public void testSalvarProjeto() throws Exception {
        mockMvc.perform(post("/projetos")
                        .flashAttr("projeto", new Projeto()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/projetos"));
    }

    @Test
    public void testExibirFormularioEditarProjeto() throws Exception {
        Long id = 1L;
        Projeto projeto = new Projeto();
        given(projetoService.encontrarPorId(id)).willReturn(Optional.of(projeto));

        mockMvc.perform(post("/projetos/{id}/editar", id))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("projeto", "gerente"))
                .andExpect(view().name("formularioEditarProjeto"));
    }

    @Test
    public void testExibirFormularioEditarProjetoSalvar() throws Exception {
        Long id = 1L;
        given(projetoService.encontrarPorId(id)).willReturn(Optional.empty());

        mockMvc.perform(post("/projetos/{id}/editar", id))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/projetos"));
    }

    @Test
    public void testSalvarFormularioEditarProjeto() throws Exception {
        Long id = 1L;
        Projeto projetoEditar = new Projeto();
        given(projetoService.editarProjeto(id, projetoEditar, projetoService)).willReturn(projetoEditar);

        mockMvc.perform(post("/projetos/{id}/editar/salvar", id)
                        .flashAttr("projetoEditar", projetoEditar))
                .andExpect(status().isOk())
                .andExpect(view().name("formularioEditarProjeto"));
    }

    @Test
    public void testDeletarProjeto() throws Exception {
        Long id = 1L;

        mockMvc.perform(post("/projetos/{id}/deletar", id))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/projetos"));
    }
}