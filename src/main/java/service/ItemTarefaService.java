package service;

import java.util.ArrayList;

import exception.TarefaException;
import model.entity.ItemTarefa;
import model.repository.ItemTarefaRepository;

public class ItemTarefaService {

	private ItemTarefaRepository itemRepository = new ItemTarefaRepository();

	public ItemTarefa inserir(ItemTarefa novoItem) throws TarefaException {
		this.validarCamposObrigatorios(novoItem);
		return itemRepository.inserir(novoItem);
	}

	public ItemTarefa consultarPorId(int id) {
		return itemRepository.consultarPorId(id);
	}

	public ArrayList<ItemTarefa> consultarTodos() {
		return itemRepository.consultarTodos();
	}

	public Boolean alterar(ItemTarefa alterarItem) {
		return itemRepository.alterar(alterarItem);
	}

	public boolean excluir(int id) throws TarefaException {
		if (itemRepository.consultarPorId(id) != null && itemRepository.consultarPorId(id).isRealizado()) {
			return itemRepository.excluir(id);
		} else {
			throw new TarefaException("Itens da tarefa não foram realizados, portanto não podem ser excluídos!");
		}
	}

	private void validarCamposObrigatorios(ItemTarefa i) throws TarefaException {

		String mensagemValidacao = "";

		if (i.getDescricao() == null || i.getDescricao().isEmpty()) {
			mensagemValidacao += " - informe o nome \n";

			if (!mensagemValidacao.isEmpty()) {
				throw new TarefaException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
			}
		}
	}
	
	public ArrayList<ItemTarefa>consultarTodosOsItensAssociadoUmaTarefa(int id){
		return itemRepository.consultarTodosOsItensAssociadoUmaTarefa(id);
		
	}
}