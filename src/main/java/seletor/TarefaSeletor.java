package seletor;

public class TarefaSeletor extends BaseSeletor {

	private String nomeTarefa;
	private String tipoTarefa;
	private Boolean realizado;
	private Boolean isTemplate;
	private Integer idUsuario;

	public TarefaSeletor(String nomeTarefa, String tipoTarefa, Boolean realizado, Boolean isTemplate,
			Integer idUsuario) {
		super();
		this.nomeTarefa = nomeTarefa;
		this.tipoTarefa = tipoTarefa;
		this.realizado = realizado;
		this.isTemplate = isTemplate;
		this.idUsuario = idUsuario;
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

	public Boolean getIsTemplate() {
		return isTemplate;
	}

	public void setIsTemplate(Boolean isTemplate) {
		this.isTemplate = isTemplate;
	}

	public Boolean getRealizado() {
		return realizado;
	}

	public void setRealizado(Boolean realizado) {
		this.realizado = realizado;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
}
