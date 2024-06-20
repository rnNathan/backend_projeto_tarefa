package model.entity;

import java.util.ArrayList;

public class Tarefa {

	private int idTarefa;
	private Usuario usuario;
	private String nomeTarefa;
	private String tipoTarefa;
	private ArrayList<ItemTarefa> itensTarefa;
	private Boolean realizado;
	private Boolean isTemplate;

	public Tarefa() {
		super();

	}
	
	public Tarefa(int idTarefa, Usuario usuario, String nomeTarefa, String tipoTarefa,
			ArrayList<ItemTarefa> itensTarefa, Boolean realizado, Boolean isTemplate) {
		super();
		this.idTarefa = idTarefa;
		this.usuario = usuario;
		this.nomeTarefa = nomeTarefa;
		this.tipoTarefa = tipoTarefa;
		this.itensTarefa = itensTarefa;
		this.realizado = realizado;
		this.isTemplate = isTemplate;
	}

	public int getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(int idTarefa) {
		this.idTarefa = idTarefa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public ArrayList<ItemTarefa> getItensTarefa() {
		return itensTarefa;
	}

	public void setItensTarefa(ArrayList<ItemTarefa> itensTarefa) {
		this.itensTarefa = itensTarefa;
	}

	public Boolean getRealizado() {
		return realizado;
	}

	public void setRealizado(Boolean realizado) {
		this.realizado = realizado;
	}

	public Boolean getIsTemplate() {
		return isTemplate;
	}

	public void setIsTemplate(Boolean isTemplate) {
		this.isTemplate = isTemplate;
	}

	
}
