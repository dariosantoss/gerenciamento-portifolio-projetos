-- -----------------------------------------------------
-- Table Pessoa
-- -----------------------------------------------------
CREATE TABLE pessoa
(
    id             bigint                 NOT NULL AUTO_INCREMENT,
    nome           VARCHAR(100)           NOT NULL,
    datanascimento DATE,
    cpf            VARCHAR(14),
    funcionario    BOOLEAN,
    gerente        BOOLEAN,
    CONSTRAINT pk_pessoa PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table Projeto
-- -----------------------------------------------------
CREATE TABLE projeto
(
    id                bigint        NOT NULL AUTO_INCREMENT,
    nome              VARCHAR(200)  NOT NULL,
    data_inicio       DATE,
    data_previsao_fim DATE,
    data_fim          DATE,
    descricao         VARCHAR(5000),
    status            VARCHAR(45),
    orcamento         FLOAT,
    risco             VARCHAR(45),
    idgerente         bigint        NOT NULL,
    CONSTRAINT pk_projeto PRIMARY KEY (id),
    CONSTRAINT fk_gerente FOREIGN KEY (idgerente)
        REFERENCES pessoa (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- -----------------------------------------------------
-- Table Membros
-- -----------------------------------------------------
CREATE TABLE membros
(
    idprojeto bigint NOT NULL,
    idpessoa  bigint NOT NULL,
    CONSTRAINT pk_membros PRIMARY KEY (idprojeto, idpessoa),
    CONSTRAINT fk_membros_projeto FOREIGN KEY (idprojeto)
        REFERENCES projeto (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_membros_pessoa FOREIGN KEY (idpessoa)
        REFERENCES pessoa (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO pessoa (nome, datanascimento, cpf, funcionario, gerente)
VALUES ('João da Silva', '1990-05-15', '123.456.789-00', true, true),
       ('Maria Souza', '1985-10-20', '987.654.321-00', false, true),
       ('Pedro Oliveira', '1978-03-25', '456.789.123-00', true, false);

INSERT INTO projeto (nome, data_inicio, data_previsao_fim, data_fim, descricao, status, orcamento, risco, idgerente)
VALUES ('Projeto A', '2024-04-01', '2024-04-15', NULL, 'Descrição do Projeto A', 'Em Andamento', 10000.00, 'Alto Risco', 2),
       ('Projeto B', '2024-04-05', '2024-05-01', NULL, 'Descrição do Projeto B', 'Planejado', 15000.00, 'Médio Risco', 2),
       ('Projeto C', '2024-04-10', '2024-04-30', NULL, 'Descrição do Projeto C', 'Em Análise', 20000.00, 'Baixo Risco', 2);
