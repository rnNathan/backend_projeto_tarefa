package controller;

import java.util.ArrayList;

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
import service.ItemTarefaService;

@Path("/restrito/item")
public class ItemTarefaController {

	private ItemTarefaService service = new ItemTarefaService();

	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ItemTarefa inserir(ItemTarefa novoItem) throws TarefaException {
		return this.service.inserir(novoItem);
	}

	@PUT
	@Path("/alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(ItemTarefa itemSelecionado) {
		return service.alterar(itemSelecionado);
	}

	@DELETE
	@Path("/excluir/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean excluir(@PathParam("id") int id) throws TarefaException {
		return this.service.excluir(id);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ItemTarefa consultarPorId(@PathParam("id") int id) {
		return service.consultarPorId(id);
	}

	@GET
	@Path("/listar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ItemTarefa> listarTodos() {
		return this.service.consultarTodos();
	}

	@GET
	@Path("/listar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ItemTarefa> consultarTodosOsItensAssociadoUmaTarefa(@PathParam("id") int id) {
		return this.service.consultarTodosOsItensAssociadoUmaTarefa(id);
	}

}
