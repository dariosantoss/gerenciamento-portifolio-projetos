<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Membros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Lista de Membros</h1>
    <a href="/membro/adicionar/form" class="btn btn-primary mt-3">Associar Membro</a>
    <a href="/" class="btn btn-primary mt-3">Listar Projetos</a>
    <table class="table mt-3">
        <thead>
        <tr>
            <th scope="col">Pessoa</th>
            <th scope="col">Projeto</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${membros}" var="membro">
            <tr>
                <td>${membro.pessoa.nome}</td>
                <td>${membro.projeto.nome}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
