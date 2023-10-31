package controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main" , "/insert" , "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DAO dao = new DAO();   
    JavaBeans contato = new JavaBeans();
    
    public Controller() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			try {
				novoContato(request, response);
			} catch (SQLIntegrityConstraintViolationException e) {
				e.printStackTrace();
			}
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			try {
				salvarContato(request, response);
			} catch (SQLIntegrityConstraintViolationException e) {
				e.printStackTrace();
			}
		} else if (action.equals("/delete")) {  
			removerContato(request, response);
		} else  {
			response.sendRedirect("index.html");
		}
	}
	
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<JavaBeans> lista =dao.listarContatos();
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLIntegrityConstraintViolationException {
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		contato.setRua(request.getParameter("rua"));
		contato.setNumero(request.getParameter("numero"));
		contato.setBairro(request.getParameter("bairro"));
		contato.setCidade(request.getParameter("cidade"));
		contato.setEstado(request.getParameter("estado"));
		
		String nome = request.getParameter("nome");
		String fone = request.getParameter("fone");
		String email = request.getParameter("email");
		String rua = request.getParameter("rua");
		String numero = request.getParameter("numero");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String regexAlfanumerico = "[a-zA-Zà-úÀ-Ú0-9\\s]+";
		String regexEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		String regexTelefone = "\\(\\d{2}\\)9\\d{4}-\\d{4}";
		
		if(nome == null || nome.isEmpty() || nome.length() < 3 || nome.length() > 25) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "O nome deve ter no mínimo 3 caracteres e no máximo 25.";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("novo.jsp");
			rd.forward(request, response);
			return;
		}else if (fone == null || fone.isEmpty() || !fone.matches(regexTelefone) || fone.length() > 14) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "Preencha o campo Telefone no formato (XX)9XXXX-XXXX";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("novo.jsp");
			rd.forward(request, response);
			return;
		}else if (email == null || email.isEmpty() || !email.matches(regexEmail) || email.length() < 5 || email.length() > 30) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "Preencha o campo Email com um formato válido.";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("novo.jsp");
			rd.forward(request, response);
			return;
		}else if (rua == null || rua.isEmpty() || !rua.matches(regexAlfanumerico) || rua.length() < 5 || rua.length() > 40) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "Preencha o campo Rua apenas com letras e números.";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("novo.jsp");
			rd.forward(request, response);
	        return;
		}else if (numero == null || numero.isEmpty() || !numero.matches(regexAlfanumerico) || numero.length() > 10) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "Preencha o campo Nº de Residência de maneira válida: somente letras e números, no máximo 10 caracteres.";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("novo.jsp");
			rd.forward(request, response);
	        return;
		}else if (bairro == null || bairro.isEmpty() || !bairro.matches(regexAlfanumerico) || bairro.length() < 5 || bairro.length() > 40) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "Preencha o campo Bairro de maneira válida: somente letras e números, no mínimo 5 caractéres e máximo de 40.";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("novo.jsp");
			rd.forward(request, response);
	        return;
		}else if (cidade == null || cidade.isEmpty() || !cidade.matches(regexAlfanumerico) || cidade.length() < 5 || cidade.length() > 30) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "Preencha o campo Cidade de maneira válida: mínimo de 5 caracteres e máximo de 30.";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("novo.jsp");
			rd.forward(request, response);
	        return;
		}else if (estado == null || estado.isEmpty() || !estado.matches(regexAlfanumerico) || estado.length() < 4 || estado.length() > 20) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "Preencha o campo Estado de maneira válida: mínimo de 5 caracteres e máximo de 20.";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("novo.jsp");
			rd.forward(request, response);
	        return;
		}else {
		dao.inserirContato(contato);
		response.sendRedirect("main");
		}
	}
	
	protected void listarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		contato.setIdcon(idcon);
		dao.selecionarContato(contato);
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		request.setAttribute("rua", contato.getRua());
		request.setAttribute("numero", contato.getNumero());
		request.setAttribute("bairro", contato.getBairro());
		request.setAttribute("cidade", contato.getCidade());
		request.setAttribute("estado", contato.getEstado());
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	protected void salvarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLIntegrityConstraintViolationException {
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		contato.setRua(request.getParameter("rua"));
		contato.setNumero(request.getParameter("numero"));
		contato.setBairro(request.getParameter("bairro"));
		contato.setCidade(request.getParameter("cidade"));
		contato.setEstado(request.getParameter("estado"));
		
		String idcon = request.getParameter("idcon");
		String nome = request.getParameter("nome");
		String fone = request.getParameter("fone");
		String email = request.getParameter("email");
		String rua = request.getParameter("rua");
		String numero = request.getParameter("numero");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String regexAlfanumerico = "^(?!\\s+$)[a-zA-Zà-úÀ-Ú0-9\\s]+$";
		String regexEmail = "^(?!\\s+$)[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		String regexTelefone = "\\(\\d{2}\\)9\\d{4}-\\d{4}";
		
		if(nome == null || nome.isEmpty() || nome.length() < 3 || nome.length() > 25) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "O nome deve ter no mínimo 3 caracteres e no máximo 25.";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("idcon", idcon);
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("editarComErro.jsp");
			rd.forward(request, response);
			return;
		}else if (fone == null || fone.isEmpty() || !fone.matches(regexTelefone) || fone.length() > 14) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "Preencha o campo Telefone no formato (XX)9XXXX-XXXX";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("idcon", idcon);
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("editarComErro.jsp");
			rd.forward(request, response);
			return;
		}else if (email == null || email.isEmpty() || !email.matches(regexEmail) || email.length() < 5 || email.length() > 30) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "Preencha o campo Email com um formato válido.";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("idcon", idcon);
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("editarComErro.jsp");
			rd.forward(request, response);
			return;
		}else if (rua == null || rua.isEmpty() || !rua.matches(regexAlfanumerico) || rua.length() < 5 || rua.length() > 40) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "Preencha o campo Rua apenas com letras e números.";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("idcon", idcon);
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("editarComErro.jsp");
			rd.forward(request, response);
	        return;
		}else if (numero == null || numero.isEmpty() || !numero.matches(regexAlfanumerico) || numero.length() > 10) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "Preencha o campo Nº de Residência de maneira válida: somente letras e números, no máximo 10 caracteres.";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("idcon", idcon);
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("editarComErro.jsp");
			rd.forward(request, response);
	        return;
		}else if (bairro == null || bairro.isEmpty() || !bairro.matches(regexAlfanumerico) || bairro.length() < 5 || bairro.length() > 40) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "Preencha o campo Bairro de maneira válida: somente letras e números, no mínimo 5 caractéres e máximo de 40.";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("idcon", idcon);
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("editarComErro.jsp");
			rd.forward(request, response);
	        return;
		}else if (cidade == null || cidade.isEmpty() || !cidade.matches(regexAlfanumerico) || cidade.length() < 5 || cidade.length() > 30) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "Preencha o campo Cidade de maneira válida: mínimo de 5 caracteres e máximo de 30.";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("idcon", idcon);
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("editarComErro.jsp");
			rd.forward(request, response);
	        return;
		}else if (estado == null || estado.isEmpty() || !estado.matches(regexAlfanumerico) || estado.length() < 4 || estado.length() > 20) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			String mensagemDeErro = "Preencha o campo Estado de maneira válida: mínimo de 5 caracteres e máximo de 20.";
			response.getWriter().write("{\"error\": \"" + mensagemDeErro + "\"}");
			request.setAttribute("idcon", idcon);
			request.setAttribute("nome", nome);
			request.setAttribute("fone", fone);
			request.setAttribute("email",email);
			request.setAttribute("rua", rua);
			request.setAttribute("numero", numero);
			request.setAttribute("bairro", bairro);
			request.setAttribute("cidade", cidade);
			request.setAttribute("estado", estado);
			request.setAttribute("erro", mensagemDeErro);
			RequestDispatcher rd = request.getRequestDispatcher("editarComErro.jsp");
			rd.forward(request, response);
	        return;
		}else {
		dao.alterarContato(contato);
		response.sendRedirect("main");
		}

	}
	
	protected void removerContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		contato.setIdcon(idcon);
		dao.deletarContato(contato);
		response.sendRedirect("main");
	}
	
	
}
