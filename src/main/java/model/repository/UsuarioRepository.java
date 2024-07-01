package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import model.dto.UsuarioDTO;
import model.entity.Usuario;
import model.enums.PerfilAcesso;
import util.StringUtils;

public class UsuarioRepository implements BaseRepository<Usuario> {

	@Override
	public Usuario inserir(Usuario novoUsuario) {

		String query = "INSERT INTO tarefa.usuario (nome, cpf, email,perfil_acesso, data_nascimento,senha) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);

		try {

			this.preencherParametrosParaInsertiOuUpdate(pstmt, novoUsuario);
			
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			
			if (resultado.next()) {
				novoUsuario.setIdUsuario(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar usuario");
			System.out.println("Erro: " + e.getMessage());

		} finally {

			Banco.closeConnection(conn);
			Banco.closeStatement(pstmt);
		}

		return novoUsuario;
	}
	
	private void preencherParametrosParaInsertiOuUpdate(PreparedStatement pstmt, Usuario novoUsuario) throws SQLException {
		pstmt.setString(1, novoUsuario.getNome());
		pstmt.setString(2, novoUsuario.getCpf());
		pstmt.setString(3, novoUsuario.getPerfil().toString());
		pstmt.setString(4, novoUsuario.getEmail());
		pstmt.setObject(5, novoUsuario.getDataNascimento());
		pstmt.setString(7, StringUtils.cifrar(novoUsuario.getSenha()));
		
		
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM tarefa.usuario WHERE id_usuario = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Usuario usuarioEditado) {
		boolean alterou = false;
		String query = " UPDATE tarefa.usuario " + " SET nome=?, email=?, data_nascimento=?, "
				+ " senha=?, perfil_acesso=? " + " WHERE id_usuario=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			this.preencherParametrosParaInsertiOuUpdate(stmt, usuarioEditado);

			stmt.setInt(7, usuarioEditado.getIdUsuario());
			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}
	
	public boolean alterarIdSessao(Usuario novoUsuario) {
		boolean alterou = false;
		String query = " UPDATE tarefa.usuario "
					 + " SET   id_sessao=? "
				     + " WHERE id_usuario=?";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		try {
			pstmt.setString(1, novoUsuario.getIdSessao());
			pstmt.setInt(2, novoUsuario.getIdUsuario());
			
			alterou = pstmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar idSessao do Usuario");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}


	@Override
	public Usuario consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		Usuario usuario = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM tarefa.usuario WHERE id_usuario = " + id;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				usuario = this.converterResultSetParaUsuario(resultado);
				
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar pessoa com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuario;
	}
	
	private Usuario converterResultSetParaUsuario(ResultSet resultado) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(resultado.getInt("ID_USUARIO"));
		usuario.setNome(resultado.getString("NOME"));
		usuario.setCpf(resultado.getString("CPF"));
		usuario.setEmail(resultado.getString("EMAIL"));
		usuario.setDataNascimento(resultado.getDate("DATA_NASCIMENTO").toLocalDate());
		usuario.setPerfil(PerfilAcesso.valueOf(resultado.getString("PERFIL_ACESSO")));
		usuario.setIdSessao(resultado.getString("ID_SESSAO"));
		return usuario;
	}

	@Override
	public ArrayList<Usuario> consultarTodos() {

		ArrayList<Usuario> usuarios = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String query = " SELECT * FROM tarefa.usuario";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Usuario usuario = this.converterResultSetParaUsuario(resultado);
				usuarios.add(usuario);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar todos usuarios");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuarios;
	}

	public boolean cpfJaCadastrado(String cpf) {
		boolean cpfJaUtilizado = false;

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		String query = " SELECT count(id_usuario) FROM tarefa.usuario WHERE cpf = " + cpf;

		try {
			ResultSet resultado = stmt.executeQuery(query);

			if (resultado.next()) {
				cpfJaUtilizado = (resultado.getInt(1) > 0);
			} else {
				cpfJaUtilizado = false;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar CPF. Causa: " + e.getMessage());
		}

		return cpfJaUtilizado;
	}
	
	public Usuario consultarPorLoginSenha(UsuarioDTO usuarioDTO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		Usuario usuario = null;
		String query = " SELECT * FROM tarefa.usuario "
				     + " WHERE email = '" + usuarioDTO.getLogin() + "'"
				     + " AND senha = '" + StringUtils.cifrar(usuarioDTO.getSenha()) + "'";
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				usuario = this.converterResultSetParaUsuario(resultado);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar usuario com login (" + usuarioDTO.getLogin() + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuario;
	}
	
	public Usuario consultarPorLogin(String login) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		Usuario usuario = new Usuario();
		String query = " SELECT * FROM tarefa.usuario "
				     + " WHERE email = '" + login + "'";
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				usuario = this.converterResultSetParaUsuario(resultado);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar usuario com login (" + login + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuario;
	}
	
	public Usuario consultarPorIdSessao(String idSessao) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		Usuario usuario = new Usuario();
		String query = " SELECT * FROM tarefa.usuario "
				     + " WHERE id_sessao = '" + idSessao + "'";
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				usuario = this.converterResultSetParaUsuario(resultado);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar usuario com idSessao (" + idSessao + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuario;
	}
	
	
	
	

}
