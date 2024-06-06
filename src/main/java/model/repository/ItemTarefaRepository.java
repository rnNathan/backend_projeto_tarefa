package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.ItemTarefa;

public class ItemTarefaRepository implements BaseRepository<ItemTarefa> {

	@Override
	public ItemTarefa inserir(ItemTarefa novoItem) {

		String query = "INSERT INTO tarefa.item (id_tarefa, descricao) VALUES (?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);

		try {
			pstmt.setInt(1, novoItem.getIdTarefa().getIdTarefa());
			pstmt.setString(2, novoItem.getDescricao());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if (resultado.next()) {
				novoItem.setIdItem(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao salvar item");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return novoItem;

	}

	@Override
	public boolean excluir(int id) {
		String query = "DELETE FROM tarefa.item where id_item =" + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;

		try {
			if (stmt.executeUpdate(query) == 1) {
				retorno = true;
			}

		} catch (SQLException e) {
			System.out.println("ERRO AO EXCLUIR UM ITEM.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}

		return retorno;
	}

	@Override
	public boolean alterar(ItemTarefa alterar) {
		String query = "UPDATE tarefa.item SET descricao=?, realizado=?  where id_item=?";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		boolean alterou = false;

		try {
			pstmt.setString(1, alterar.getDescricao());
			pstmt.setBoolean(2, alterar.isRealizado());
			
			pstmt.setInt(3, alterar.getIdItem());
			alterou = pstmt.executeUpdate() > 0;

		} catch (Exception e) {
			System.out.println("Erro ao atualizar item");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);

		}
		return alterou;
	}

	@Override
	public ItemTarefa consultarPorId(int id) {
		String query = "SELECT descricao, id_item FROM tarefa.item where id_item = " + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ItemTarefa itemTarefa = null;
		ResultSet resultado = null;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				itemTarefa = new ItemTarefa();
				itemTarefa.setIdItem(resultado.getInt("id_item"));
				itemTarefa.setDescricao(resultado.getString("descricao"));
				itemTarefa.setRealizado(resultado.getBoolean("realizado"));

			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar item por ID.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return itemTarefa;
	}

	@Override
	public ArrayList<ItemTarefa> consultarTodos() {
		ArrayList<ItemTarefa> listaItemTarefa = new ArrayList<ItemTarefa>();

		String query = "SELECT descricao, id_item, realizado FROM tarefa.item";
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ItemTarefa itemTarefa = null;
		ResultSet resultado = null;

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				itemTarefa = new ItemTarefa();
				itemTarefa.setIdItem(resultado.getInt("id_item"));
				itemTarefa.setDescricao(resultado.getString("descricao"));
				itemTarefa.setRealizado(resultado.getBoolean("realizado"));
				listaItemTarefa.add(itemTarefa);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os itens no banco.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaItemTarefa;
	}

	public ArrayList<ItemTarefa> consultarTodosOsItensAssociadoUmaTarefa(int id) {
		ArrayList<ItemTarefa> listaItemTarefa = new ArrayList<ItemTarefa>();
		String query = "SELECT * FROM  tarefa.item where id_tarefa =" + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ItemTarefa itemTarefa = null;
		ResultSet resultado = null;

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				itemTarefa = new ItemTarefa();
				itemTarefa.setIdItem(resultado.getInt("id_item"));
				itemTarefa.setDescricao(resultado.getString("descricao"));
				itemTarefa.setRealizado(resultado.getBoolean("realizado"));
				listaItemTarefa.add(itemTarefa);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os itens associado a uma tarefa.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}

		return listaItemTarefa;

	}

	public ArrayList<ItemTarefa> consultarItensPedentes(int id) {
		ArrayList<ItemTarefa> listaItemTarefa = new ArrayList<ItemTarefa>();
		String query = "SELECT * FROM tarefa.item where id_tarefa = " + id + " AND realizado = false";
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ItemTarefa itemTarefa = null;
		ResultSet resultado = null;

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				itemTarefa = new ItemTarefa();
				itemTarefa.setIdItem(resultado.getInt("id_item"));
				itemTarefa.setDescricao(resultado.getString("descricao"));
				itemTarefa.setRealizado(resultado.getBoolean("realizado"));
				listaItemTarefa.add(itemTarefa);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar itens pendentes.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}

		return listaItemTarefa;

	}

	public boolean marcarItemComoRealizado(int idItem) {
		String query = "UPDATE tarefa.item set realizado = true where id_item = " + idItem;
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		boolean retorno = false;

		try {
			retorno = pstmt.executeUpdate() > 0;

		} catch (Exception e) {
			System.out.println("ERRO AO MARCA ITEM REALIZADO.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);

		}

		return retorno;

	}

}
