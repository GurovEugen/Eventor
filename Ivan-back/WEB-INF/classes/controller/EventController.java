import org.json.JSONException;
import org.json.JSONObject;
import sun.text.normalizer.ICUData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Inject
    private SelectedGroup SG;

    @Inject
    private Event event;

    @Inject
    private Group group;



   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public String getEvent(@QueryParam("id") Integer id) {

        JSONObject eventJson = new JSONObject();
        event = SE.getEventById(id);
        group = SG.getGroupById(event.getGroupId());
        String startDate = event.getStartDate();
        String endDate = event.getEndDate();
        String status = "Нет данных";
        Date sDate = null;
        Date eDate = null;
        Date cDate = null;
        try {
            sDate = new SimpleDateFormat("dd.MM.yyyy").parse(startDate);
            eDate = new SimpleDateFormat("dd.MM.yyyy").parse(endDate);
            cDate = new Date();
        }
        catch (ParseException ex){


        }

        if(sDate.getTime() <= cDate.getTime() && cDate.getTime() <= eDate.getTime())
        {
            status = "Текущее";
        }
        else if (sDate.getTime() > cDate.getTime())
        {
            status = "Не началось";
        }
        else
        {
            status = "Закончено";
        }
       try {
            eventJson.put("name",event.getName());
            eventJson.put("startDate",event.getStartDate());
            eventJson.put("endDate",event.getEndDate());
            eventJson.put("place",event.getPlace());
            eventJson.put("info",event.getInfo());
            eventJson.put("groupName",group.getName());
            eventJson.put("status",status);
        }

        catch (JSONException ex)
        {


        }


        return eventJson.toString();
   }
}