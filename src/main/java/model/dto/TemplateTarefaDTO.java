package model.dto;

;

public class TemplateTarefaDTO {

	private int idTarefaTemplate;
	private String nomeNovaTarefa;

	public TemplateTarefaDTO() {
		super();

	}

	public TemplateTarefaDTO(int idTarefaTemplate, String nomeTarefa) {
		super();
		this.idTarefaTemplate = idTarefaTemplate;
		this.nomeNovaTarefa = nomeTarefa;
	}

	public int getIdTarefaTemplate() {
		return idTarefaTemplate;
	}

	public void setIdTarefaTemplate(int idTarefaTemplate) {
		this.idTarefaTemplate = idTarefaTemplate;
	}

	public String getNomeNovaTarefa() {
		return nomeNovaTarefa;
	}

	public void setNomeNovaTarefa(String nomeNovaTarefa) {
		this.nomeNovaTarefa = nomeNovaTarefa;
	}

}
