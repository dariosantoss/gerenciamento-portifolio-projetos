package br.com.projetos.restController;

import br.com.projetos.model.Pessoa;
import br.com.projetos.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PessoaControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaController pessoaController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCadastrarPessoaWhenValidPessoaThenReturnCreated() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João da Silva");
        pessoa.setDataNascimento(new Date());
        pessoa.setCpf("123.456.789-00");
        pessoa.setFuncionario(true);
        pessoa.setGerente(false);

        Pessoa savedPessoa = new Pessoa();
        savedPessoa.setId(1L);
        savedPessoa.setNome(pessoa.getNome());
        savedPessoa.setDataNascimento(pessoa.getDataNascimento());
        savedPessoa.setCpf(pessoa.getCpf());
        savedPessoa.setFuncionario(pessoa.isFuncionario());
        savedPessoa.setGerente(pessoa.isGerente());

        when(pessoaService.salvarPessoa(any(Pessoa.class))).thenReturn(savedPessoa);

        String pessoaJson = objectMapper.writeValueAsString(pessoa);

        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pessoaJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value(pessoa.getNome()))
                .andExpect(jsonPath("$.cpf").value(pessoa.getCpf()));
    }
}