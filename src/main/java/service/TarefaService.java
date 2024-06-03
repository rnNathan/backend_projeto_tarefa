package service;

import java.util.ArrayList;

import model.entity.Tarefa;
import model.repository.TarefaRepository;

public class TarefaService {

	private TarefaRepository tarefaRepository = new TarefaRepository();

	public Tarefa inserir(Tarefa novoTarefa) {
		return tarefaRepository.inserir(novoTarefa);
	}

	public Tarefa consultarPorId(int id) {
		return tarefaRepository.consultarPorId(id);
	}

	public ArrayList<Tarefa> consultarTodos() {
		return tarefaRepository.consultarTodos();
	}

	public Boolean alterar(Tarefa alterarTarefa) {
		return tarefaRepository.alterar(alterarTarefa);
	}

	public boolean excluir(int id) {
		// Aplicar regra de negócio de que caso uma tarefa não tenha sido executada,
		// não pode ser excluida!
		return this.tarefaRepository.excluir(id);
	}

}