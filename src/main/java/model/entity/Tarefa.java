package model.entity;

import java.util.ArrayList;

public class Tarefa {

	private int idTarefa;
	private String nomeTarefa;
	private String tipoTarefa;
	private ArrayList<ItemTarefa> itensTarefa;
	private boolean realizado;

	public Tarefa() {
		super();

	}

	public Tarefa(int idTarefa, String nomeTarefa, String tipoTarefa, ArrayList<ItemTarefa> itensTarefa,
			boolean realizado) {
		super();
		this.idTarefa = idTarefa;
		this.nomeTarefa = nomeTarefa;
		this.tipoTarefa = tipoTarefa;
		this.itensTarefa = itensTarefa;
		this.realizado = realizado;
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

}
