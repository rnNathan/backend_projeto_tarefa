package controller;

import exception.TarefaException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Usuario;
import service.UsuarioService;

@Path("/usuario")
public class UsuarioController {

	private UsuarioService usuarioService = new UsuarioService();

	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario inserir(Usuario novoUsuario) throws TarefaException {
		return this.usuarioService.inserir(novoUsuario);
	}

	@PUT
	@Path("/alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Usuario novoUsuario) throws TarefaException {
		return usuarioService.alterar(novoUsuario);
	}

	@DELETE
	@Path("/excluir/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean excluir(@PathParam("id") int id) throws TarefaException {
		return this.usuarioService.excluir(id);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario consultarPorId(@PathParam("id") int id) {
		return usuarioService.consultarPorId(id);
	}

}
