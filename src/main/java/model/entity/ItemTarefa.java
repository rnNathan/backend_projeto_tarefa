package model.entity;

public class ItemTarefa {

	private int idItem;
	private String descricao;
	private boolean realizado;

	public ItemTarefa(int idItem, String descricao, boolean realizado) {
		super();
		this.idItem = idItem;
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
