package model.repository;

import java.util.ArrayList;

//BaseRepository com o tipo <T> é usado como interface e outras 
//classe herdarem todos os metodos dessa class

public interface BaseRepository<T> {

	// salvar no banco.
	public T inserir(T novoObjeto);

	// excluir uma entidade do banco;
	public boolean excluir(int id);

	// alterar um objeto usando a classe do objeto.
	public boolean alterar(T objeto);

	// consultar uma entidade do banco por id, retornando objeto inteiro.
	public T consultarPorId(int id);

	// listar todas entidades do banco de dados.
	public ArrayList<T> consultarTodos();

}
