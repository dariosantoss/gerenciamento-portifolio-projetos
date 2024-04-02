package br.com.projetos.enums;

public enum Risco {
    BAIXO_RISCO("Baixo Risco"),
    MÉDIO_RISCO("Médio Risco"),
    ALTO_RISCO("Alto Risco");

    private final String descricao;

    Risco(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
