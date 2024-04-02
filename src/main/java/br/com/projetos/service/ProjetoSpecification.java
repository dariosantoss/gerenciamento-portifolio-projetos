package br.com.projetos.service;

import br.com.projetos.dto.ProjetoFiltroDTO;
import br.com.projetos.model.Projeto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ProjetoSpecification {

    public static Specification<Projeto> buscarPorNomeEStatus(ProjetoFiltroDTO filtroDTO) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicateNome = filtroDTO.getNome() != null ? criteriaBuilder.like(root.get("nome"), "%" + filtroDTO.getNome() + "%") : null;
            Predicate predicateStatus = null;
            if(filtroDTO.getStatus() != null){
               predicateStatus = filtroDTO.getStatus().getDescricao() != null &&  filtroDTO.getStatus() != null ? criteriaBuilder.equal(root.get("status"), filtroDTO.getStatus().getDescricao()) : null;
            }

            if (predicateNome != null && predicateStatus != null) {
                return criteriaBuilder.and(predicateNome, predicateStatus);
            } else if (predicateNome != null) {
                return predicateNome;
            } else if (predicateStatus != null) {
                return predicateStatus;
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }
}
