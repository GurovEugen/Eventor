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

   
@Named("user") 
@SessionScoped
@Path("/event")
public class SelectedEvent implements Serializable {


   private DataSource ds;
   
 
    @Resource
   private UserTransaction userTransaction;
   @Inject
   private Event eventFind;


   @PersistenceUnit(unitName = "eventPersistenceUnit")
   private EntityManagerFactory entityManagerFactory;
  
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Event getEvent(@QueryParam("id") Integer id) {
   	   String res = "";
	   EntityManager entityManager;
	   try {
	      entityManager = entityManagerFactory.createEntityManager();
	   }
       catch (Exception e) {
		 res = "EntityManager: " + e.getMessage();
		 return null;
	   }	   

		  
          try 
	      {
                userTransaction.begin();
         		entityManager.joinTransaction();
         		eventFind = entityManager.find(Event.class,id);
				userTransaction.commit();		
          }		  
			catch (Exception e) {
         		res = "JPA: " + e.getMessage(); 
       		}
	   return eventFind;
   }
}