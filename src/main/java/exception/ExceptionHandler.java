package exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ExceptionHandler implements ExceptionMapper<Exception> {

	protected final Logger log = Logger.getLogger(getClass().getName());

	@Override
	public Response toResponse(Exception exception) {
		Map<String, String> mapAtributos = new HashMap<String, String>();
		mapAtributos.put("mensagem", exception.getMessage());
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		mapAtributos.put("Stracktrace", sw.toString());
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(mapAtributos);
		
		
		return Response.status(Status.BAD_REQUEST)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.entity(json)
				.build();
	}
	
}
