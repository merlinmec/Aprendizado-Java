package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {
	//parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "123456";
	
	//métodos de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			return con;
		}catch (Exception e){
		    System.out.println(e);
		    return null;
		}
	}
	
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,telefone,email,rua,num,bairro,cidade,estado) values (?,?,?,?,?,?,?,?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getRua());
			pst.setString(5, contato.getNumero());
			pst.setString(6, contato.getBairro());
			pst.setString(7, contato.getCidade());
			pst.setString(8, contato.getEstado());
			pst.executeUpdate();
			con.close();
		}catch (SQLException error) {
			throw new RuntimeException(error.getMessage()); 
		}
	}
	
	public ArrayList<JavaBeans> listarContatos(){
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by  nome";
		try {
			Connection con = conectar ();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				String rua = rs.getString(5);
				String numero = rs.getString(6);
				String bairro = rs.getString(7);
				String cidade = rs.getString(8);
				String estado = rs.getString(9);
				contatos.add(new JavaBeans(idcon, nome, fone, email,rua,numero,bairro,cidade,estado));
			}
			con.close();
			return contatos;
		}catch (Exception e){
		    System.out.println(e);
		    return null;
		}
	}
	
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
				contato.setRua(rs.getString(5));
				contato.setNumero(rs.getString(6));
				contato.setBairro(rs.getString(7));
				contato.setCidade(rs.getString(8));
				contato.setEstado(rs.getString(9));
			}
			con.close();
		}catch (SQLException error) {
			throw new RuntimeException(error.getMessage());
		}
	}
	
	public void alterarContato(JavaBeans contato) {
		String create = "update contatos set nome = ?, telefone = ?, email = ?, rua = ?, num = ?, bairro = ?, cidade = ?, estado = ? where idcon =?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());	
			pst.setString(2, contato.getFone());
			pst.setString(3,  contato.getEmail());
			pst.setString(4, contato.getRua());
			pst.setString(5, contato.getNumero());
			pst.setString(6, contato.getBairro());
			pst.setString(7, contato.getCidade());
			pst.setString(8, contato.getEstado());
			pst.setString(9, contato.getIdcon());
			
			
			pst.executeUpdate();
			con.close();
		}catch (SQLException error) {
			throw new RuntimeException(error.getMessage());
		}
	}
	
	
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon =?";
		try {
			Connection con = conectar ();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		}catch (SQLException error) {
			throw new RuntimeException(error.getMessage());
		}
	}
	
	
}