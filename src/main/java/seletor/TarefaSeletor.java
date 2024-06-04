package seletor;

public class TarefaSeletor extends BaseSeletor {
	
	private String nomeTarefa;
	private String tipoTarefa;
	private boolean realizado;
	
	
	public TarefaSeletor(String nome, String tipoTarefa, boolean realizado ) {
		super();
		this.nomeTarefa = nome;
		this.tipoTarefa = tipoTarefa;
		this.realizado = realizado;

	}
	
	public TarefaSeletor() {
		super();
		// TODO Auto-generated constructor stub
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

	public boolean isRealizado() {
		return realizado;
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}
	
	
	
	
	

}
