<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Novo Projeto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Novo Projeto</h1>
    <a href="/" class="btn btn-primary mt-3">Listar Projetos</a>
    <form action="${pageContext.request.contextPath}/projetos" method="post">
        <div class="mb-3">
            <label for="nome" class="form-label">Nome do Projeto:</label>
            <input type="text" class="form-control" id="nome" name="nome" required>
        </div>

        <div class="mb-3">
            <label for="dataInicio" class="form-label">Data de Início:</label>
            <input type="date" class="form-control" id="dataInicio" name="dataInicio">
        </div>

        <div class="mb-3">
            <label for="dataPrevisaoFim" class="form-label">Data Prevista de Término:</label>
            <input type="date" class="form-control" id="dataPrevisaoFim" name="dataPrevisaoFim">
        </div>

        <div class="mb-3">
            <label for="dataFim" class="form-label">Data de Término:</label>
            <input type="date" class="form-control" id="dataFim" name="dataFim">
        </div>

        <div class="mb-3">
            <label for="descricao" class="form-label">Descrição:</label>
            <textarea class="form-control" id="descricao" name="descricao" rows="4" required></textarea>
        </div>

        <div class="mb-3">
            <label for="risco" class="form-label">Risco:</label>
            <select class="form-select" id="risco" name="risco">
                <option value="BAIXO_RISCO">Baixo Risco</option>
                <option value="MÉDIO_RISCO">Médio Risco</option>
                <option value="ALTO_RISCO">Alto Risco</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="orcamento" class="form-label">Orçamento:</label>
            <input type="number" class="form-control" id="orcamento" name="orcamento" step="0.01" min="0">
        </div>

        <div class="mb-3">
            <label for="status" class="form-label">Status:</label>
            <select class="form-select" id="status" name="status">
                <option value="Em Análise">Em Análise</option>
                <option value="Análise Realizada">Análise Realizada</option>
                <option value="Análise Aprovada">Alto Risco</option>
                <option value="Iniciado">Iniciado</option>
                <option value="Planejado">Planejado</option>
                <option value="Em Andamento">Em andamento</option>
                <option value="Encerrado">Encerrado</option>
                <option value="Cancelado">Cancelado</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="gerente" class="form-label">Gerente:</label>
            <select class="form-select" id="gerente" name="gerente" required>
                <c:forEach items="${gerente}" var="pessoa">
                    <option value="${pessoa.id}">${pessoa.nome}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Salvar</button>
    </form>
</div>
</body>
</html>
