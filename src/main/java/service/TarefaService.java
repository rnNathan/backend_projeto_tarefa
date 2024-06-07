package service;

import java.util.ArrayList;
import java.util.List;

import exception.TarefaException;
import model.DTO.TemplateTarefaDTO;
import model.entity.ItemTarefa;
import model.entity.Tarefa;
import model.repository.ItemTarefaRepository;
import model.repository.TarefaRepository;
import seletor.TarefaSeletor;

public class TarefaService {

	private TarefaRepository tarefaRepository = new TarefaRepository();
	private ItemTarefaRepository itemRepository = new ItemTarefaRepository();

	public Tarefa inserir(Tarefa novaTarefa) throws TarefaException {
		this.validarCamposObrigatorios(novaTarefa);
		return tarefaRepository.inserir(novaTarefa);
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

	public Tarefa criarTarefaAPartirDeTemplate(TemplateTarefaDTO templateDTO) throws TarefaException {

		Tarefa tarefaTemplate = tarefaRepository.consultarPorId(templateDTO.getIdTarefaTemplate());

		if (tarefaTemplate == null || tarefaTemplate.isTemplate() == false) {
			throw new TarefaException("Tarefa não é um template");
		}

		// Cria nova tarefa
		Tarefa novaTarefa = new Tarefa();
		novaTarefa.setUsuario(tarefaTemplate.getUsuario());
		novaTarefa.setTipoTarefa(tarefaTemplate.getTipoTarefa());
		novaTarefa.setNomeTarefa(templateDTO.getNomeNovaTarefa());
		novaTarefa = tarefaRepository.inserir(novaTarefa);

		// Cria os itens da nova tarefa
		for (ItemTarefa itemTarefaTemplate : tarefaTemplate.getItensTarefa()) {
			ItemTarefa novoItem = new ItemTarefa();
			novoItem.setDescricao(itemTarefaTemplate.getDescricao());
			novoItem.setTarefa(novaTarefa);

			itemRepository.inserir(novoItem);
		}

		// Chamado para atualizar a lista de itens
		novaTarefa = tarefaRepository.consultarPorId(novaTarefa.getIdTarefa());
		return novaTarefa;
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

	private void validarCamposObrigatorios(Tarefa t) throws TarefaException {

		String mensagemValidacao = "";
		if (t.getNomeTarefa() == null || t.getNomeTarefa().isEmpty()) {
			mensagemValidacao += " - informe o nome \n";
		}
		if (t.getTipoTarefa() == null || t.getTipoTarefa().isEmpty()) {
			mensagemValidacao += " - informe a data de nascimento \n";
		}
		if (!mensagemValidacao.isEmpty()) {

			throw new TarefaException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
		}
	}

}
