package seletor;

public class TarefaSeletor extends BaseSeletor {
	
	private String nomeTarefa;
	private String tipoTarefa;
	private Boolean realizado;
	
	
	public TarefaSeletor(String nome, String tipoTarefa, Boolean realizado ) {
		super();
		this.nomeTarefa = nome;
		this.tipoTarefa = tipoTarefa;
		this.realizado = realizado;

	}
	
	public TarefaSeletor() {
		super();
	}
	
	public String getNomeTarefa() {
		return nomeTarefa;
	}
	public void setNomeTarefa(String nome) {
		this.nomeTarefa = nome;
	}
	public String getTipoTarefa() {
		return tipoTarefa;
	}
	public void setTipoTarefa(String tipoTarefa) {
		this.tipoTarefa = tipoTarefa;
	}

	public Boolean isRealizado() {
		return realizado;
	}

	public void setRealizado(Boolean realizado) {
		this.realizado = realizado;
	}
	
	
	
	
	

}
