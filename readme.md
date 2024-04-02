# Sistema de Gerenciamento de Portfólio de Projetos

Este é um sistema desenvolvido em Java 17 e Spring Boot 3.2.4, utilizando JSP para a interface do usuário, que tem como objetivo gerenciar os dados do portfólio de projetos de uma empresa.

## Funcionalidades Principais:

1. **Cadastro de Projetos:**
    - Adição de novos projetos.
    - Atualização de informações de projetos existentes.
    - Remoção de projetos.

2. **Visualização de Projetos:**
    - Lista de todos os projetos cadastrados, com detalhes como nome, descrição, status, data de início, data de conclusão prevista, etc.

3. **Associar Membros ao projeto:**
    - Associa membros funcionários aos projetos.

4. **API - cadastrar pessoa:**
    - Cadstra pessoas via webservice.
    - POST - localhost:8080/pessoas
```json
{
  "nome": "João Silva 2",
  "dataNascimento": "1990-01-01",
  "cpf": "123.456.789-00",
  "funcionario": true,
  "gerente": true
}
```

## Tecnologias Utilizadas:

- **Java 17:** Linguagem de programação principal.
- **Spring MVC:** Framework para desenvolvimento de aplicações Java.
- **Spring Boot 3.2.4:** Framework para desenvolvimento de aplicações Java.
- **JSP (JavaServer Pages):** Para a criação da interface do usuário.
- **Banco de Dados:** H2.
- **HTML/CSS:** Para a estilização da interface do usuário.
- **Git:** Controle de versão do código-fonte.
- **Maven:** Gerenciamento de dependências.

## Pré-Requisitos:

- Java JDK 17 ou superior instalado.
- Ambiente de desenvolvimento configurado para desenvolvimento Spring Boot.
- Banco de dados configurado e acessível.

## Configuração:

1. Configure o banco de dados conforme necessário, atualizando as configurações de conexão no arquivo `application.properties`.
```
spring.application.name=projetos
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp

port=8080
spring.datasource.url=jdbc:h2:mem:projetos
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
2. Abra o projeto em sua IDE de preferência.
3. Certifique-se de ter todas as dependências necessárias configuradas.
4. Execute a aplicação e acesse-a via navegador web.

