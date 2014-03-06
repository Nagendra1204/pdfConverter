package glu.gameon.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.core.util.Base64;

@Path("/")
public class PdfResource {

	Logger LOGGER = LoggerFactory.getLogger(PdfResource.class);

	@POST
	@Path("convert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/pdf")
	public Response getPdfFile(Map<String, Object> jsonData) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			byte[] data = Base64.decode(jsonData.get("base64").toString());

			File file = new File("test.pdf");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.close();
			return Response.ok(file, "application/pdf").build();
		} catch (Exception e) {
			LOGGER.error("Error occured while converting");
			response.put("status", 500);
			response.put("error", "INTERNAL_SERVER_ERROR");
			return Response.ok().entity(response).build();
		}
	}
}
