package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Tarefa;
import seletor.TarefaSeletor;

public class TarefaRepository implements BaseRepository<Tarefa> {

	@Override
	public Tarefa inserir(Tarefa novaTarefa) {
		String query = "INSERT INTO tarefa.tarefas (nome, tipo, itens) VALUES (?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);

		try {

			pstmt.setString(1, novaTarefa.getNomeTarefa());
			pstmt.setString(2, novaTarefa.getTipoTarefa());
			pstmt.setObject(3, novaTarefa.getItensTarefa());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if (resultado.next()) {
				novaTarefa.setIdTarefa(resultado.getInt(1));
				;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao salvar tarefa");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return novaTarefa;
	}

	@Override
	public boolean excluir(int idTarefa) {
		String query = "DELETE FROM tarefa.tarefas where id_tarefa =" + idTarefa;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;

		try {
			if (stmt.executeUpdate(query) == 1) {
				retorno = true;
			}

		} catch (SQLException e) {
			System.out.println("ERRO AO EXCLUIR UMA TAREFA.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}

		return retorno;
	}

	@Override
	public boolean alterar(Tarefa tarefaSelecionada) {
		String query = "UPDATE tarefa.tarefas SET nome=?, tipo=?, itens=? where id_tarefa]]=?";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		boolean alterou = false;

		try {
			pstmt.setString(1, tarefaSelecionada.getNomeTarefa());
			pstmt.setString(2, tarefaSelecionada.getTipoTarefa());
			pstmt.setObject(3, tarefaSelecionada.getItensTarefa());
			alterou = pstmt.executeUpdate() > 0;

		} catch (Exception e) {
			System.out.println("Erro ao atualizar tarefa");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);

		}

		return alterou;
	}
	

	@Override
	public Tarefa consultarPorId(int id) {
		String query = "SELECT * FROM tarefa.tarefas where id_tarefa = " + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		Tarefa tarefa = null;
		ResultSet resultado = null;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				tarefa = new Tarefa();
				tarefa.setIdTarefa(resultado.getInt("id_tarefa"));
				tarefa.setNomeTarefa(resultado.getString("nome"));

				ItemTarefaRepository itemTarefa = new ItemTarefaRepository();
				tarefa.setItensTarefa(itemTarefa.consultarPorId(resultado.getInt("iditem")));

			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar tarefa por ID.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return tarefa;
	}

	@Override
	public ArrayList<Tarefa> consultarTodos() {
		ArrayList<Tarefa> listaTarefas = new ArrayList<Tarefa>();

		String query = "SELECT * FROM tarefa.tarefas";
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		Tarefa tarefa = null;
		ResultSet resultado = null;

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				tarefa = new Tarefa();
				tarefa.setIdTarefa(resultado.getInt("id_tarefa"));
				tarefa.setNomeTarefa(resultado.getString("nome"));
				ItemTarefaRepository itemTarefa = new ItemTarefaRepository();
				ItemTarefaRepository repository = new ItemTarefaRepository();
				tarefa.setItensTarefa(repository.consultarPorId(resultado.getInt("iditem")));
				listaTarefas.add(tarefa);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos as tarefas no banco.");
			System.out.println("ERRO: " + e.getMessage());
		}

		return listaTarefas;
	}

	public ArrayList<Tarefa> consultarPorFiltro(TarefaSeletor seletor) {
		ArrayList<Tarefa> listaTarefas = new ArrayList<Tarefa>();

		String query = "select t.* from tarefa.tarefas t ";
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		Tarefa tarefa = null;
		ResultSet resultado = null;
		
		query = incluirFiltros(seletor, query);
		
		if (seletor.temPaginacao()) {
			query += " limit " + seletor.getLimite();
			query += " offset " + seletor.getOffset();
		}
		
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				tarefa = new Tarefa();
				tarefa.setIdTarefa(resultado.getInt("id_tarefa"));
				tarefa.setNomeTarefa(resultado.getString("nome"));
				ItemTarefaRepository itemTarefa = new ItemTarefaRepository();
				tarefa.setItensTarefa(itemTarefa.consultarPorId(resultado.getInt("iditem")));
				listaTarefas.add(tarefa);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos as tarefas no banco.");
			System.out.println("ERRO: " + e.getMessage());
		}

		return listaTarefas;
	}

	private String incluirFiltros(TarefaSeletor seletor, String query) {
		boolean primeiro = true;

		if (seletor.getNome() != null) {
			if (primeiro) {
				query += " where ";
			} else {
				query += " and ";
			}
		}

		query += " upper(t.nome) like upper ('%" + seletor.getNome() + "%')";

		if (seletor.getTipoTarefa() != null) {
			if (primeiro) {
				query += " where ";

			} else {
				query += " and ";
			}
			query += " upper(t.tipo_tarefa) like upper ('%" + seletor.getTipoTarefa() + "%')";
		}

		return query;

	}
	
	public int contarTotalDeRegistro(TarefaSeletor seletor) {
		ArrayList<Tarefa> listaTarefas = new ArrayList<Tarefa>();

		String query = "select t.* from tarefa.tarefas t ";
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		int totalRegistros = 0;
		
		query = incluirFiltros(seletor, query);
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				totalRegistros += resultado.getInt(1);
				}
		} catch (Exception e) {
			System.out.println("ERRO AO CONSULTAR TOTAL DE TAREFAS!");
			System.out.println("ERRO: " + e.getMessage());
		}finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		
		return totalRegistros;
	}
	
	public int contarPaginas(TarefaSeletor seletor) {
		int totalPaginas = 0;
		int totalRegistros = this.contarTotalDeRegistro(seletor);

		totalPaginas = totalRegistros / seletor.getLimite();
		int resto = totalRegistros % seletor.getLimite();

		if (resto > 0) {
			totalPaginas++;
		}

		return totalPaginas;

	}


}
