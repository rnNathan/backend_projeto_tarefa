package service;

import java.util.ArrayList;
import java.util.List;


import exception.TarefaException;
import model.entity.Tarefa;
import model.entity.Usuario;
import model.enums.PerfilAcesso;
import model.repository.TarefaRepository;
import model.repository.UsuarioRepository;
import seletor.TarefaSeletor;

public class UsuarioService {

	private static final int MINIMO_CARACTERES = 5;
	private static final int MAXIMO_CARACTERES = 12;
	private UsuarioRepository repository = new UsuarioRepository();
	private TarefaRepository tarefaRepository = new TarefaRepository();

	public Usuario inserir(Usuario novoUsuario) throws TarefaException {

		this.validarCamposObrigatorios(novoUsuario);
		validarCpf(novoUsuario);
		validarPerfilUsuario(novoUsuario);

		return repository.inserir(novoUsuario);
	}
	
	private void validarPerfilUsuario(Usuario umUsuario) {
		if(umUsuario.getPerfil() == null) {
			umUsuario.setPerfil(PerfilAcesso.USUARIO);
		}
	}
	

	public boolean alterar(Usuario usuarioEditado) throws TarefaException {
		validarPerfilUsuario(usuarioEditado);
		
		return repository.alterar(usuarioEditado);
	}

	public boolean excluir(int id) throws TarefaException {
		
		TarefaSeletor seletor = new TarefaSeletor();
		seletor.setIdUsuario(id);
		
		ArrayList<Tarefa> listaTarefasUsuario = tarefaRepository.consultarPorFiltro(seletor);
		if (listaTarefasUsuario.size() > 0) {
			throw new TarefaException("Não pode ser excluído um usuário pois tem uma tarefa associada.");
		} else {
			return repository.excluir(id);
		}

	}

	public Usuario consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	public List<Usuario> consultarTodas() {
		return repository.consultarTodos();
	}
	
	public Usuario consultarPorIdSessao(String idSessao) {
		return this.repository.consultarPorIdSessao(idSessao);
	}

	private void validarCpf(Usuario novoUsuario) throws TarefaException {
		if (repository.cpfJaCadastrado(novoUsuario.getCpf())) {
			throw new TarefaException("CPF " + novoUsuario.getCpf() + " já cadastrado ");
		}
	}

	private void validarCamposObrigatorios(Usuario u) throws TarefaException {
		String mensagemValidacao = "";

		if (u.getNome() == null || u.getNome().isEmpty()) {
			mensagemValidacao += " - informe o nome \n";
		}
		if (u.getDataNascimento() == null) {
			mensagemValidacao += " - informe a data de nascimento \n";
		}
		if (u.getCpf() == null || u.getCpf().isEmpty() || u.getCpf().length() != 11) {
			mensagemValidacao += " - informe o CPF \n";
		}
		if (u.getEmail().isEmpty() || u.getEmail() == null) {
			mensagemValidacao += " - informe o email \n";
		}
		if (u.getSenha().length() < MINIMO_CARACTERES || u.getSenha().length() > MAXIMO_CARACTERES) {
			mensagemValidacao += " - insira uma senha entre 5 e 12 caracteres \n";
		}
		if (!mensagemValidacao.isEmpty()) {
			throw new TarefaException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
		}
	}

	private void validarCamposPermitidos(Usuario usuarioEditado) throws TarefaException {
		Usuario usuarioOriginal = repository.consultarPorId(usuarioEditado.getIdUsuario());
		if (usuarioOriginal == null) {
			throw new TarefaException("Usuário não encontrado.");
		}
		if (!usuarioEditado.getNome().equalsIgnoreCase(usuarioOriginal.getNome().trim())) {
			throw new TarefaException("O nome não pode ser alterado.");
		}
		if (!usuarioEditado.getCpf().equalsIgnoreCase(usuarioOriginal.getCpf())) {
			throw new TarefaException("O CPF não pode ser alterado.");
		}
	}
	
	
}