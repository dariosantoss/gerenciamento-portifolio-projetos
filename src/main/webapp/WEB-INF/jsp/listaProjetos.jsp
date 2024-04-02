<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Projetos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Lista de Projetos</h1>
    <a href="/projetos/novo" class="btn btn-primary mt-3">Novo Projeto</a>
    <a href="/membro/listar" class="btn btn-primary mt-3">Listar Membros</a>

    <form action="/buscar-projetos" method="GET" class="mt-3">
        <div class="row">
            <div class="col">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" class="form-control">
            </div>
            <div class="col">
                <label for="status">Status:</label>
                <select id="status" name="status" class="form-control">
                    <option value="">Selecione...</option>
                    <option value="EM_ANALISE">Em Análise</option>
                    <option value="ANALISE_REALIZADA">Análise Realizada</option>
                    <option value="ANALISE_APROVADA">Alto Risco</option>
                    <option value="INICIADO">Iniciado</option>
                    <option value="PLANEJADO">Planejado</option>
                    <option value="EM_ANDAMENTO">Em andamento</option>
                    <option value="ENCERRADO">Encerrado</option>
                    <option value="CANCELADO">Cancelado</option>
                </select>
            </div>
            <div class="col align-self-end">
                <button type="submit" class="btn btn-primary">Pesquisar</button>
            </div>
        </div>
    </form>

    <table class="table table-striped mt-3">
        <thead>
        <tr>
            <th>Nome</th>
            <th>Data Início</th>
            <th>Data Previsão Fim</th>
            <th>Data Fim</th>
            <th>Descrição</th>
            <th>Status</th>
            <th>Orçamento</th>
            <th>Risco</th>
            <th>Gerente</th>
            <th>Ações</th> <!-- Nova coluna para as ações -->
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${projetos}" var="projeto">
            <tr>
                <td>${projeto.nome}</td>
                <td><fmt:formatDate value="${projeto.dataInicio}" pattern="dd/MM/yyyy" /></td>
                <td><fmt:formatDate value="${projeto.dataPrevisaoFim}" pattern="dd/MM/yyyy" /></td>
                <td><fmt:formatDate value="${projeto.dataFim}" pattern="dd/MM/yyyy" /></td>
                <td>${projeto.descricao}</td>
                <td>${projeto.status}</td>
                <td>${projeto.orcamento}</td>
                <td>${projeto.risco}</td>
                <td>${projeto.gerente.nome}</td>
                <td>
                    <form action="/projetos/${projeto.id}/editar" method="post">
                        <button type="submit" class="btn btn-primary" style="font-size: 8px;"><i class="fas fa-edit"></i></button>
                    </form>
                    <form action="/projetos/${projeto.id}/deletar" method="post" onsubmit="return confirm('Tem certeza que deseja excluir o projeto?')">
                        <button type="submit" class="btn btn-danger" style="font-size: 8px;"><i class="fas fa-trash-alt"></i></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
