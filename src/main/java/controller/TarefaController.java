package controller;

import java.util.ArrayList;
import java.util.List;

import exception.TarefaException;
import filter.AuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import model.dto.TemplateTarefaDTO;
import model.entity.ItemTarefa;
import model.entity.Tarefa;
import model.entity.Usuario;
import model.enums.PerfilAcesso;
import seletor.TarefaSeletor;
import service.TarefaService;
import service.UsuarioService;

@Path("/restrito/tarefa")
public class TarefaController {

	private TarefaService tarefaService = new TarefaService();
	private UsuarioService usuarioService = new UsuarioService();
	
	@Context
	private HttpServletRequest request;

	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tarefa inserir(Tarefa novaTarefa) throws TarefaException {
		return this.tarefaService.inserir(novaTarefa);
	}

	@PUT
	@Path("/alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Tarefa tarefaAlterada) {
		return tarefaService.alterar(tarefaAlterada);
	}

	@DELETE
	@Path("/excluir/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean excluir(@PathParam("id") int id) throws TarefaException {
		return this.tarefaService.excluir(id);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Tarefa consultarPorId(@PathParam("id") int id) {
		return tarefaService.consultarPorId(id);
	}

	@GET
	@Path("/listar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Tarefa> consultarTodos() {		
		return this.tarefaService.consultarTodos();
	}
	
	/**
	 * Caso o filtro seja por idUsuario é verificar se é do proprio usuário e ou do admin.
	 * @param seletor
	 * @return
	 * @throws TarefaException
	 */
	
	@Path("/filtro")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Tarefa> consultarPorFiltro(TarefaSeletor seletor) throws TarefaException {
		
		String idSessao = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		Usuario usuario = usuarioService.consultarPorIdSessao(idSessao);
		
		if (seletor.getIdUsuario() != null) {
			if (usuario.getPerfil() == PerfilAcesso.USUARIO && usuario.getIdUsuario() != seletor.getIdUsuario()) {
				throw new TarefaException("Usuário sem acesso.");
			}
		}
		
		return tarefaService.consultarPorFiltro(seletor);
	}

	@Path("/contar-pagina")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int contarPaginas(TarefaSeletor seletor) {
		return this.tarefaService.contarPaginas(seletor);
	}

	@Path("/contar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int contarTotalDeRegistro(TarefaSeletor seletor) {
		return this.tarefaService.contarTotalRegistro(seletor);
	}

	@Path("/contar-template")
	@GET
	public List<Tarefa> listaTemplate() {
		return tarefaService.listaTemplate();
	}

	@POST
	@Path("/inserir-template")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tarefa criarTarefaAPartirDeTemplate(TemplateTarefaDTO dto) throws TarefaException {
		return this.tarefaService.criarTarefaAPartirDeTemplate(dto);
	}
	
	@PUT
	@Path("/concluido")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Tarefa concluirTarefa() {
		return tarefaService.concluirTarefa();
	}
}
