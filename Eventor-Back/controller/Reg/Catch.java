package controller;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

@Path("/reg")
public class Catch {
 @POST
 public Response ping()
 {          
    return Response.ok("OK").build();
 }
}