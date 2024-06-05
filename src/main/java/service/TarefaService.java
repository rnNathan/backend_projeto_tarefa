package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.entity.ItemTarefa;
import model.entity.Tarefa;
import model.repository.ItemTarefaRepository;
import model.repository.TarefaRepository;
import seletor.TarefaSeletor;

public class TarefaService {

	private TarefaRepository tarefaRepository = new TarefaRepository();
	private ItemTarefaRepository  item = new ItemTarefaRepository();

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
	
	public Tarefa concluirTarefa(int idTarefa) {
		
		ArrayList<ItemTarefa> list = item.consultarItensPedentes(idTarefa);
		Tarefa tarefa = new Tarefa();
		
		for (ItemTarefa itemTarefa : list) {
			item.marcaItemComoRealizado(itemTarefa.getIdItem());
		}
		
		tarefa = tarefaRepository.consultarPorId(idTarefa);
		
		return tarefa;
		
		
	}
	
	

}
