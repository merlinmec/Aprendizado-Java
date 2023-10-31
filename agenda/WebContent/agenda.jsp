<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
    ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)request.getAttribute("contatos");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="Botao1">Novo Contato</a>
	
	<table id="tabela">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Email</th>
				<th>Rua</th>
				<th>Nº da Residência</th>
				<th>Bairro</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%for (int i = 0; i < lista.size(); i++) { %>
				<tr>
					<td style= "text-align:center"><%=lista.get(i).getIdcon()%></td>
					<td style= "text-align:center"><%=lista.get(i).getNome()%></td>
					<td style= "text-align:center"><%=lista.get(i).getFone()%></td>
					<td style= "text-align:center"><%=lista.get(i).getEmail()%></td>
					<td style= "text-align:center"><%=lista.get(i).getRua()%></td>
					<td style= "text-align:center"><%=lista.get(i).getBairro()%></td>
					<td style= "text-align:center"><%=lista.get(i).getNumero()%></td>
					<td style= "text-align:center"><%=lista.get(i).getCidade()%></td>
					<td style= "text-align:center"><%=lista.get(i).getEstado()%></td>
					<td style= "text-align:center"><a href="select?idcon=<%=lista.get(i).getIdcon() %>" class="Botao1">Editar</a> 
					<a href="delete?idcon=<%=lista.get(i).getIdcon() %>" class="Botao2">Excluir</a>
					</td>
				</tr>
			<%} %>
		</tbody>
	</table>
	<script> src="scripts/confirmador.js"</script>
</body>
</html>