package seletor;

public class TarefaSeletor extends BaseSeletor {
	
	private String nome;
	private String tipoTarefa;
	
	
	public TarefaSeletor(String nome, String tipoTarefa) {
		super();
		this.nome = nome;
		this.tipoTarefa = tipoTarefa;
	}
	
	public TarefaSeletor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipoTarefa() {
		return tipoTarefa;
	}
	public void setTipoTarefa(String tipoTarefa) {
		this.tipoTarefa = tipoTarefa;
	}
	
	
	

}
