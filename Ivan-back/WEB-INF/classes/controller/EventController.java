import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.*;
import javax.ws.rs.core.MediaType;

   
//@Named("user")
@SessionScoped
@Path("/event")
public class EventController implements Serializable {


	@Inject
    private SelectedEvent SE;

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Event getEvent(@QueryParam("id") Integer id) {

	   return SE.getEventById(id);
   }
}