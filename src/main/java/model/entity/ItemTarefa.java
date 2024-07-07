package model.entity;

public class ItemTarefa {

	private int idItem;
	private Integer idTarefa;
	private String descricao;
	private Boolean realizado;

	public ItemTarefa(int idItem, Integer idTarefa, String descricao, Boolean realizado) {
		super();
		this.idItem = idItem;
		this.idTarefa = idTarefa;
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

	public Integer getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(Integer idTarefa) {
		this.idTarefa = idTarefa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getRealizado() {
		return realizado;
	}

	public void setRealizado(Boolean realizado) {
		this.realizado = realizado;
	}



}
