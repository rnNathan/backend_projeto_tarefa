package service;

import java.util.UUID;

import exception.TarefaException;
import model.dto.UsuarioDTO;
import model.entity.Usuario;
import model.repository.UsuarioRepository;



public class LoginService {

	private UsuarioRepository repository = new UsuarioRepository();
	
	public Usuario autenticar(UsuarioDTO usuarioDTO) throws TarefaException {
		
		if(usuarioDTO == null || 
			(usuarioDTO.getLogin() == null || usuarioDTO.getLogin().trim().isEmpty())) {
			throw new TarefaException("Usuário não informado");
		}
		
		if(usuarioDTO.getSenha() == null || usuarioDTO.getSenha().trim().isEmpty()) {
			throw new TarefaException("Senha não informada");
		}
		
		Usuario usuarioAutenticado = repository.consultarPorLoginSenha(usuarioDTO);
		
		if(usuarioAutenticado == null) {
			throw new TarefaException("Login ou senha inválidos, tente novamente");
		}

		String idSessao = UUID.randomUUID().toString();
		usuarioAutenticado.setIdSessao(idSessao);
		repository.alterarIdSessao(usuarioAutenticado);
		
		return usuarioAutenticado;
	}

	public boolean chaveValida(String idSessao) {
		Usuario usuario = this.repository.consultarPorIdSessao(idSessao);

		return usuario != null 
				&& usuario.getIdSessao() != null
				&& usuario.getIdSessao().equals(idSessao);
	}
}
