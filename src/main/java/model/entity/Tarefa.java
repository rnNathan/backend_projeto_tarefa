package model.entity;

import java.util.ArrayList;

public class Tarefa {

	private int idTarefa;
	private Usuario idUsuario;
	private String nomeTarefa;
	private String tipoTarefa;
	private ArrayList<ItemTarefa> itensTarefa;
	private boolean realizado;
	private boolean isTemplate;

	public Tarefa() {
		super();

	}

	public Tarefa(int idTarefa, Usuario idUsuario, String nomeTarefa, String tipoTarefa,
			ArrayList<ItemTarefa> itensTarefa, boolean realizado, boolean isTemplate) {
		super();
		this.idTarefa = idTarefa;
		this.idUsuario = idUsuario;
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

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
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

	public boolean isRealizado() {
		return realizado;
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}

	public boolean isTemplate() {
		return isTemplate;
	}

	public void setTemplate(boolean isTemplate) {
		this.isTemplate = isTemplate;
	}

}
