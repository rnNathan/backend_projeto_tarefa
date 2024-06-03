package model.entity;

public class ItemTarefa {

	private int idItem;
	private String descricao;

	public ItemTarefa(int idItem, String descricao) {
		super();
		this.idItem = idItem;
		this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
