package controller;

import java.util.ArrayList;
import java.util.List;

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
import model.entity.ItemTarefa;
import model.entity.Tarefa;
import seletor.TarefaSeletor;
import service.TarefaService;

@Path("/tarefa")
public class TarefaController {

	private TarefaService tarefaService = new TarefaService();

	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tarefa inserir(Tarefa novaTarefa) {
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

	@Path("/filtro")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Tarefa> consultarPorFiltro(TarefaSeletor seletor) {
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
	public Tarefa criarTarefaAPartirDeTemplate(int id) {
		return this.tarefaService.criarTarefaAPartirDeTemplate(id);
	}
	
	@PUT
	@Path("/concluido")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Tarefa concluirTarefa() {
		return tarefaService.concluirTarefa();
	}
}
