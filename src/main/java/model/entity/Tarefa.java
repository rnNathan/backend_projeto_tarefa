package model.entity;

public class Tarefa {

	private int idTarefa;
	private String nomeTarefa;
	private String tipoTarefa;
	private ItemTarefa itensTarefa;

	public Tarefa() {
		super();

	}

	public Tarefa(int idTarefa, String nomeTarefa, String tipoTarefa, ItemTarefa itensTarefa) {
		super();
		this.idTarefa = idTarefa;
		this.nomeTarefa = nomeTarefa;
		this.tipoTarefa = tipoTarefa;
		this.itensTarefa = itensTarefa;
	}

	public int getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(int idTarefa) {
		this.idTarefa = idTarefa;
	}

	public String getNomeTarefa() {
		return nomeTarefa;
	}

	public void setNomeTarefa(String nomeTarefa) {
		this.nomeTarefa = nomeTarefa;
	}

	public String getTipoTarefa() {
		return tipoTarefa;
	}

	public void setTipoTarefa(String tipoTarefa) {
		this.tipoTarefa = tipoTarefa;
	}

	public ItemTarefa getItensTarefa() {
		return itensTarefa;
	}

	public void setItensTarefa(ItemTarefa descricao) {
		this.itensTarefa = descricao;
	}

}
