package service;

import java.util.List;

import exception.TarefaException;
import model.entity.Usuario;
import model.repository.UsuarioRepository;

public class UsuarioService {

	private static final int MINIMO_CARACTERES = 5;
	private static final int MAXIMO_CARACTERES = 12;
	private UsuarioRepository repository = new UsuarioRepository();

	public Usuario inserir(Usuario novoUsuario) throws TarefaException {
		validarCamposObrigatorios(novoUsuario);

		validarCpf(novoUsuario);

		return repository.inserir(novoUsuario);
	}

	public boolean alterar(Usuario usuarioEditado) {
		validarCamposObrigatorios(usuarioEditado);

		return repository.alterar(usuarioEditado);
	}

	public boolean excluir(int id) {
		return repository.excluir(id);
	}

	public Usuario consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	public List<Usuario> consultarTodas() {
		return repository.consultarTodos();
	}

	private void validarCpf(Usuario novoUsuario) throws TarefaException {
		if (repository.cpfJaCadastrado(novoUsuario.getCpf())) {
			throw new TarefaException("CPF " + novoUsuario.getCpf() + " já cadastrado ");
		}
	}

	private void validarCamposObrigatorios(Usuario u) {
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
		if (u.getEmail().isEmpty()) {
			mensagemValidacao += " - informe o email \n";
		}
		if (u.getSenha().equals(MINIMO_CARACTERES) || u.getSenha().equals(MAXIMO_CARACTERES)) {
			mensagemValidacao += " - insira uma senha entre 5 e 12 caracteres \n";
		}
		if (u.getLogin() == null) {
			mensagemValidacao += " - informe o login ";
		}

		if (!mensagemValidacao.isEmpty()) {

			System.out.println(("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao));
		}
	}

}
