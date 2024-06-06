package model.entity;

public class ItemTarefa {

	private int idItem;
	private Tarefa tarefa;
	private String descricao;
	private boolean realizado;

	public ItemTarefa(int idItem, Tarefa idTarefa, String descricao, boolean realizado) {
		super();
		this.idItem = idItem;
		this.tarefa = idTarefa;
		this.descricao = descricao;
		this.realizado = realizado;
	}

	public ItemTarefa() {
		super();
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa idTarefa) {
		this.tarefa = idTarefa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isRealizado() {
		return realizado;
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}

}
