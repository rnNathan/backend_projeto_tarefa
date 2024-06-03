package service;

import java.util.ArrayList;

import model.entity.ItemTarefa;
import model.repository.ItemTarefaRepository;

public class ItemTarefaService {
	
	private ItemTarefaRepository itemRepository = new ItemTarefaRepository();
	
	
	public ItemTarefa inserir (ItemTarefa novoItem) {
		return itemRepository.inserir(novoItem);
	}
	
	public ItemTarefa consultarPorId(int id) {
		return itemRepository.consultarPorId(id);
	}
	
	public ArrayList<ItemTarefa> consultarTodos (){
		return itemRepository.consultarTodos();
	}
	
	public Boolean alterar(ItemTarefa alterarItem) {
		return itemRepository.alterar(alterarItem);
	}
	
	public boolean excluir (int id) {
		return this.itemRepository.excluir(id);
	}

}
