package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.ItemTarefa;
import model.entity.Tarefa;
import seletor.TarefaSeletor;

public class TarefaRepository implements BaseRepository<Tarefa> {

	@Override
	public Tarefa inserir(Tarefa novaTarefa) {
		String query = "INSERT INTO tarefa.tarefas (nome_tarefa, tipo_tarefa) VALUES (?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);

		try {

			pstmt.setString(1, novaTarefa.getNomeTarefa());
			pstmt.setString(2, novaTarefa.getTipoTarefa());
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
		String query = "UPDATE tarefa.tarefas SET nome_tarefa=?, tipo_tarefa=?, realizado=? where id_tarefa =?";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		boolean alterou = false;

		try {
			pstmt.setString(1, tarefaSelecionada.getNomeTarefa());
			pstmt.setString(2, tarefaSelecionada.getTipoTarefa());
			pstmt.setObject(3, tarefaSelecionada.getItensTarefa());
			pstmt.setBoolean(4, tarefaSelecionada.isRealizado());
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
				tarefa.setNomeTarefa(resultado.getString("nome_tarefa"));
				tarefa.setTipoTarefa(resultado.getString("tipo_tarefa"));
				tarefa.setRealizado(resultado.getBoolean("realizado"));
				ItemTarefaRepository itemTarefa = new ItemTarefaRepository();
				tarefa.setItensTarefa(
						itemTarefa.consultarTodosOsItensAssociadoUmaTarefa(resultado.getInt("id_tarefa")));

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
				tarefa.setNomeTarefa(resultado.getString("nome_tarefa"));
				tarefa.setRealizado(resultado.getBoolean("realizado"));
				ItemTarefaRepository itemTarefa = new ItemTarefaRepository();
				ItemTarefaRepository repository = new ItemTarefaRepository();
				tarefa.setItensTarefa(
						repository.consultarTodosOsItensAssociadoUmaTarefa(resultado.getInt("id_tarefa")));
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
				tarefa.setNomeTarefa(resultado.getString("nome_tarefa"));
				tarefa.setTipoTarefa(resultado.getString("tipo_tarefa"));
				tarefa.setRealizado(resultado.getBoolean("realizado"));
				ItemTarefaRepository itemTarefa = new ItemTarefaRepository();
				tarefa.setItensTarefa(
						itemTarefa.consultarTodosOsItensAssociadoUmaTarefa(resultado.getInt("id_tarefa")));
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
		} finally {
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

	// Método para criar uma nova tarefa a partir de um template
	public void criarTarefaAPartirDeTemplate(int idTarefaTemplate) {

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultadoTarefaTemplate = null;
		ResultSet resultadoItensTemplate = null;

		// CONSULTAR TAREFA TEMPLATE
		String queryTarefaTemplate = "SELECT * FROM tarefa.tarefas WHERE id_tarefa = " + idTarefaTemplate
				+ " AND is_template = TRUE";

		try {
			resultadoTarefaTemplate = stmt.executeQuery(queryTarefaTemplate);

			if (resultadoTarefaTemplate.next()) {
				Tarefa tarefa = new Tarefa();

				// String nomeTarefa = resultadoTarefaTemplate.getString("nome_tarefa");
				// String tipoTarefa = resultadoTarefaTemplate.getString("tipo_tarefa");
				// Insere nova tarefa
				// String insertTarefa = "INSERT INTO tarefas (nome_tarefa, tipo_tarefa) VALUES
				// (?, ?)";
				// PreparedStatement psInsertTarefa = conn.prepareStatement(insertTarefa,
				// Statement.RETURN_GENERATED_KEYS);

				String insertTarefa = "INSERT INTO tarefas (nome_tarefa, tipo_tarefa) VALUES (?, ?)";
				PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, insertTarefa);
				pstmt.setString(1, tarefa.getNomeTarefa());
				pstmt.setString(2, tarefa.getTipoTarefa());
				pstmt.executeUpdate();

				ResultSet resultado = pstmt.getGeneratedKeys();
				if (resultado.next()) {
					tarefa.setIdTarefa(resultado.getInt(1));
				}

				// Consulta os itens template
				String queryItensTemplate = "SELECT * FROM tarefa.item WHERE id_tarefa = " + idTarefaTemplate;
				resultadoItensTemplate = stmt.executeQuery(queryItensTemplate);

				// Insere os itens da nova tarefa
				while (resultadoItensTemplate.next()) {
					ItemTarefa item = new ItemTarefa();

					// String descricao = resultadoItensTemplate.getString("descricao");
					// boolean realizadoItem = resultadoItensTemplate.getBoolean("realizado");

					String insertItem = "INSERT INTO itens_tarefa (id_tarefa, descricao) VALUES (?, ?)";
					PreparedStatement pstmt2 = Banco.getPreparedStatementWithPk(conn, insertItem);
					pstmt2.setInt(1, item.getIdItem());
					pstmt2.setString(2, item.getDescricao());
					pstmt2.executeUpdate();
				}
			}

		} catch (SQLException e) {
			System.out.println("Erro ao criar tarefa a partir do template.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultadoTarefaTemplate);
			Banco.closeResultSet(resultadoItensTemplate);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
	}
}
