package model.entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario {

	private int idUsuario;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private String email;
	private String login;
	private String senha;
	private ArrayList<Tarefa> listaTarefas;

	public Usuario() {
		super();
	}

	public Usuario(int idUsuario, String nome, String cpf, LocalDate dataNascimento, String email, String login,
			String senha, ArrayList<Tarefa> listaTarefas) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.listaTarefas = listaTarefas;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ArrayList<Tarefa> getListaTarefas() {
		return listaTarefas;
	}

	public void setListaTarefas(ArrayList<Tarefa> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}

}
