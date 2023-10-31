<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>	
<body>
	<h1>Editar Contato</h1>
	<form name="formContato" action="update">
		<div style= "visibility:hidden"><%out.println(request.getAttribute("idcon"));%></div>
		<table>
			<tr>
				<td><input type ="text" name="nome" class="Caixa1" value="<%out.println(request.getAttribute("nome"));%>" maxlength="25" placeholder ="Nome "></td>
			</tr>
			<tr>
				<td><input type ="text" name="fone" class="Caixa2" value="<%out.println(request.getAttribute("fone"));%>" onkeypress="$(this).mask('(00)90000-0000');" placeholder = "Telefone" ></td>
			</tr>
			<tr>
				<td><input type ="text" name="email" class="Caixa1" value="<%out.println(request.getAttribute("email"));%>" placeholder = "Email" ></td>
			</tr>
			<tr>
				<td><input type ="text" name="rua" class="Caixa1" value="<%out.println(request.getAttribute("rua"));%>" maxlength="40" placeholder = "Rua" ></td>
			</tr>
			<tr>
				<td><input type ="text" name="numero" class="Caixa1" value="<%out.println(request.getAttribute("numero"));%>" maxlength="10" placeholder = "Nº da Residência"></td>
			</tr>
			<tr>
				<td><input type ="text" name="bairro" class="Caixa1" value="<%out.println(request.getAttribute("bairro"));%>" maxlength="40" placeholder = "Bairro"></td>
			</tr>
			<tr>
				<td><input type ="text" name="cidade" class="Caixa1" value="<%out.println(request.getAttribute("cidade"));%>" maxlength="30" placeholder = "Cidade" ></td>
			</tr>
			<tr>
				<td><input type ="text" name="estado" class="Caixa1" value="<%out.println(request.getAttribute("estado"));%>" maxlength="20" placeholder = "Estado" ></td>
			</tr>
			<tr>
				<td><input type ="hidden" name="idcon" class="caixa3" value=<%out.println(request.getAttribute("idcon"));%> readonly></td>
			</tr>
			
		</table>
		<button value="Salvar" onclick="update" class="Botao1">Salvar</button>
	</form>
</body>
</html>