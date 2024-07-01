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
import jakarta.ws.rs.ext.Provider;


@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

	protected final Logger log = Logger.getLogger(getClass().getName());

	@Override
	public Response toResponse(Exception exception) {
		String json = converterExceptionParaJson(exception);

		return Response.status(Status.BAD_REQUEST)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.entity(json)
				.build();
	}

	public static String converterExceptionParaJson(Exception exception) {
		Map<String, String> mapAtributos = new HashMap<String, String>();
		mapAtributos.put("mensagem", exception.getMessage());

		//Fonte: https://www.baeldung.com/java-stacktrace-to-string
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		
		mapAtributos.put("stacktrace", sw.toString());

		//Definando o Json que será retornado.
		Gson gson = new GsonBuilder().create();

		
		return gson.toJson(mapAtributos);
	}

	
	
	
}
