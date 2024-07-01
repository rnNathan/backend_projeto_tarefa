package controller;


import exception.TarefaException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.dto.UsuarioDTO;
import model.entity.Usuario;
import service.LoginService;

@Path("/login")
public class LoginController{
	
	private LoginService loginService = new LoginService();
	
	@POST
	@Path("/autenticar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario autenticar(UsuarioDTO usuarioTentandoAutenticar) throws TarefaException {
		return loginService.autenticar(usuarioTentandoAutenticar);
	}
}
