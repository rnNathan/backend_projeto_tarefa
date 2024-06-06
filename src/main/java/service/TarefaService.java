package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exception.TarefaException;
import model.entity.ItemTarefa;
import model.entity.Tarefa;
import model.repository.ItemTarefaRepository;
import model.repository.TarefaRepository;
import seletor.TarefaSeletor;

public class TarefaService {

	private TarefaRepository tarefaRepository = new TarefaRepository();
	private ItemTarefaRepository itemRepository = new ItemTarefaRepository();

	public Tarefa inserir(Tarefa novoTarefa) {
		return tarefaRepository.inserir(novoTarefa);
	}

	public Tarefa consultarPorId(int id) {
		return tarefaRepository.consultarPorId(id);
	}

	public ArrayList<Tarefa> consultarTodos() {
		return tarefaRepository.consultarTodos();
	}

	public boolean alterar(Tarefa alterarTarefa) {
		return tarefaRepository.alterar(alterarTarefa);
	}

	public boolean excluir(int id) throws TarefaException {
		if (tarefaRepository.consultarPorId(id) != null && tarefaRepository.consultarPorId(id).isRealizado()) {
			return tarefaRepository.excluir(id);
		} else {
			throw new TarefaException("Tarefa não foi realizada, portanto não pode ser excluídos!");
		}
	}

	public ArrayList<Tarefa> consultarPorFiltro(TarefaSeletor seletor) {
		return tarefaRepository.consultarPorFiltro(seletor);
	}

	public int contarTotalRegistro(TarefaSeletor seletor) {
		return this.tarefaRepository.contarTotalDeRegistro(seletor);
	}

	public int contarPaginas(TarefaSeletor seletor) {
		return this.tarefaRepository.contarPaginas(seletor);
	}

	public List<Tarefa> listaTemplate() {
		return tarefaRepository.listarTemplates();
	}

	public Tarefa criarTarefaAPartirDeTemplate(int id) {
		return tarefaRepository.criarTarefaAPartirDeTemplate(id);
	}

	public Tarefa concluirTarefa() {

		Tarefa tarefa = new Tarefa();
		ArrayList<ItemTarefa> list = itemRepository.consultarItensPedentes(tarefa.getIdTarefa());
		
		for (ItemTarefa itemTarefa : list) {
			itemRepository.marcarItemComoRealizado(itemTarefa.getIdItem());
		}

		tarefa = tarefaRepository.consultarPorId(tarefa.getIdTarefa());

		return tarefa;

	}

}
