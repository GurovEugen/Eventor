import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SessionScoped
@Path("/controller")
public class Controller implements Serializable {
    //----------------Авторизация и регистрация-----------------
    @Inject
    private authModel authModelClass;
    @Inject
    private regModel regModelClass;
    @Inject
    private tokenModel tokenModelClass;
    //---------------События и добавление события-----------------
    @Inject
    private Model data;
    //---------------Выбрать событие--------------------
    @Inject
    private SelectedEvent SE;
    @Inject
    private SelectedGroup SG;
    @Inject
    private Event event;
    @Inject
    private Group group;
    @Inject
    private SelectedUser SU;
    @Inject
    private User user;

    @Path("/reg")
    @POST
    public String regControllerFunc(String response) throws JSONException {
        JSONObject userJson = new JSONObject(response);
        String message = regModelClass.reg(userJson);
        return message;
    }

    @Path("/auth")
    @POST
    @Produces("application/json")
    public String authControllerFunc(String response) throws JSONException, NoSuchAlgorithmException {
        JSONObject userJson = new JSONObject(response);
        Integer id = authModelClass.auth(userJson);
        if (id == -200){
            JSONObject err = new JSONObject();
            err.put("error", "Пользователь не найден в базе");
            return err.toString();
        }
        else if(id != 0){
            String token = tokenModelClass.setToken(id);
            if (token != ""){
                JSONObject idAndToken = new JSONObject();
                idAndToken.put("id_user", id);
                idAndToken.put("token", token);
                return idAndToken.toString();
            }
            else {
                JSONObject err = new JSONObject();
                err.put("error", "Ошибка получения токена");
                return err.toString();
            }
        }
        return null;
    }

    @Path("/checkToken")
    @POST
    public Boolean checkToken(String request) throws JSONException {
        JSONObject userJson = new JSONObject(request);
        Boolean success = tokenModelClass.tokenAccess(userJson);
        return success;
    }

    @Path("/removeToken")
    @POST
    public Boolean removeToken(String request) throws JSONException {
        JSONObject userJson = new JSONObject(request);
        Boolean success = tokenModelClass.tokenRemove(userJson);
        return success;
    }

    @Path("/events")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> events() throws JSONException{
        List <Event> eventList = data.getEvents();
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

    @Path("/event")
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
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
            sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            eDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            cDate = new SimpleDateFormat("yyyy-MM-dd").parse(formatter.format(new Date()));
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

    @Path("/user")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@QueryParam("id") Integer id) {
        return SU.getUserById(id);
    }

    @Path("/user")
    @POST
    public String editUser(String editingUser) throws JSONException {
        JSONObject editingUs = new JSONObject(editingUser);
        return SU.editUser(editingUs);
    }

}