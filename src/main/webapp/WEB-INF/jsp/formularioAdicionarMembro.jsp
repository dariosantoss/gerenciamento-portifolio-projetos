<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Adicionar Membro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Adicionar Membro</h1>
    <a href="/membro/listar" class="btn btn-primary mt-3">Voltar para Lista de Membros</a>
    <form action="${pageContext.request.contextPath}/membro/adicionar" method="post">
        <div class="mb-3">
            <label for="pessoa" class="form-label">Pessoa:</label>
            <select id="pessoa" name="pessoa.id" class="form-select" required>
                <c:forEach items="${pessoas}" var="pessoa">
                    <option value="${pessoa.id}">${pessoa.nome}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="projeto" class="form-label">Projeto:</label>
            <select id="projeto" name="projeto.id" class="form-select" required>
                <c:forEach items="${projetos}" var="projeto">
                    <option value="${projeto.id}">${projeto.nome}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Adicionar Membro</button>
    </form>
</div>
</body>
</html>
