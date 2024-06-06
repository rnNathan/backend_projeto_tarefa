package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Usuario;

public class UsuarioRepository implements BaseRepository<Usuario> {

	@Override
	public Usuario inserir(Usuario novoUsuario) {

		String query = "insert into tarefa.usuario (nome, cpf, email, data_nascimento, login, senha) values (?, ?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);

		try {

			pstmt.setString(1, novoUsuario.getNome());
			pstmt.setString(2, novoUsuario.getCpf());
			pstmt.setString(3, novoUsuario.getEmail());
			pstmt.setObject(4, novoUsuario.getDataNascimento());
			pstmt.setString(5, novoUsuario.getLogin());
			pstmt.setString(6, novoUsuario.getSenha());
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
		String query = " UPDATE tarefa.usuario " + " SET nome=?, cpf=?, email=?, data_nascimento=?, "
				+ " login=?, senha=? " + " WHERE id_usuario=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setString(1, usuarioEditado.getNome());
			stmt.setString(2, usuarioEditado.getCpf());
			stmt.setString(3, usuarioEditado.getEmail() + "");
			stmt.setDate(4, Date.valueOf(usuarioEditado.getDataNascimento()));
			stmt.setString(5, usuarioEditado.getLogin());
			stmt.setString(6, usuarioEditado.getSenha());

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
				usuario = new Usuario();
				usuario.setIdUsuario(resultado.getInt("ID_USUARIO"));
				usuario.setNome(resultado.getString("NOME"));
				usuario.setCpf(resultado.getString("CPF"));
				usuario.setDataNascimento(resultado.getDate("DATA_NASCIMENTO").toLocalDate());
				usuario.setLogin(resultado.getString("LOGIN"));
				usuario.setSenha(resultado.getString("SENHA"));
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
				Usuario usuario = new Usuario();
				usuario = new Usuario();
				usuario.setIdUsuario(resultado.getInt("ID_USUARIO"));
				usuario.setNome(resultado.getString("NOME"));
				usuario.setCpf(resultado.getString("CPF"));
				usuario.setDataNascimento(resultado.getDate("DATA_NASCIMENTO").toLocalDate());
				usuario.setLogin(resultado.getString("LOGIN"));
				usuario.setSenha(resultado.getString("SENHA"));

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
}
