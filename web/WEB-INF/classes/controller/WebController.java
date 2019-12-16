

import javax.ws.rs.*;
import java.io.Serializable;
import javax.inject.Named;

  
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.json.*;


import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



//@Named("user") // or @ManagedBean(name="user")
@SessionScoped
@Path("/data")
public class WebController implements Serializable{

   //ПЕРЕМЕННЫЕ
   @Inject
   private Model data;

   //МЕТОДЫ
   @Path("/events")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List <Event> events() throws JSONException{

      List <Event> eventList = data.getEvents();
      /*
      JSONObject obj = new JSONObject();
      JSONArray finalJson = new JSONArray();
      for (Event e : eventList) {

         obj.put("eventId", e.getEventId());
         obj.put("eventName", e.getEventName());
         obj.put("eventInfo", e.getEventInfo());
         obj.put("eventPlace", e.getEventPlace());
         obj.put("eventGroupId", e.getEventGroupId());
         obj.put("eventUserId", e.getEventUserId());
         obj.put("eventStartDate", e.getEventStartDate());
         obj.put("eventEndDate", e.getEventEndDate());

         finalJson.put(obj);

         obj.remove("eventId");
         obj.remove("eventName");
         obj.remove("eventInfo");
         obj.remove("eventPlace");
         obj.remove("eventGroupId");
         obj.remove("eventStartDate");
         obj.remove("eventEndDate");
         obj.remove("eventUserId");

      }

       */
      return eventList;

   }

   @Path("/groups")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List <Group> groups() {

      List <Group> groupList = data.getGroups();

      return groupList;

   }

   @Path("/setevent")
   @POST
   public String setEvent(String newEvent) throws JSONException {

      JSONObject newEv = new JSONObject(newEvent);

      return data.setNewEvent(newEv);

   }

   @Path("/date")
   @GET
   public String getDate()  {

      Date currentDate = Calendar.getInstance().getTime();
      DateFormat formatter = new SimpleDateFormat("yyyy,MM,dd");
      String today = formatter.format(currentDate);
      try{
         currentDate = new Date();
      }
      catch (Exception e){

      }

      return  today;

   }

}