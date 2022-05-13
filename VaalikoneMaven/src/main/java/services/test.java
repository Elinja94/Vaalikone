package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/test")

public class test {
	
	@Path("/hello")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHello() {
		return "This is working correctly";
		
	}

}
