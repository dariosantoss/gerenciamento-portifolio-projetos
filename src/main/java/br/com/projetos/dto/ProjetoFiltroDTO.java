package br.com.projetos.dto;

import br.com.projetos.enums.StatusAnalise;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjetoFiltroDTO {
    private String nome;
    private StatusAnalise status;
}
