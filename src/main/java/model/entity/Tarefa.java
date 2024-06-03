package model.entity;

public class Tarefa {
	
	private int idTarefa;
	private String nomeTarefa;
	private String tipoTarefa;
	private ItemTarefa descricao;
	
	
	public Tarefa() {
		super();

	}
	public Tarefa(int idTarefa,String nomeString, String tipoTarefa, ItemTarefa descricao) {
		super();
		this.idTarefa = idTarefa;
		this.nomeTarefa = nomeTarefa;	
		this.tipoTarefa = tipoTarefa;
		this.descricao = descricao;
	}
	public int getIdTarefa() {
		return idTarefa;
	}
	public void setIdTarefa(int idTarefa) {
		this.idTarefa = idTarefa;
	}
	public String getTipoTarefa() {
		return tipoTarefa;
	}
	public void setTipoTarefa(String tipoTarefa) {
		this.tipoTarefa = tipoTarefa;
	}
	public ItemTarefa getDescricao() {
		return descricao;
	}
	public void setDescricao(ItemTarefa descricao) {
		this.descricao = descricao;
	}
	
	
	
	
	
	
	
	

}
