import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.Serializable;

@SessionScoped
@Path("/controller")
public class controller implements Serializable {
    @Inject
    private authModel authModelClass;

    @Path("/reg")
    @POST
    public String regControllerFunc(String response) throws JSONException {
        JSONObject userJson = new JSONObject(response);
        String password = userJson.getString("password");
        return "Я твой сервер, а твой пароль " + password;
    }

    @Path("/auth")
    @POST
    public String authControllerFunc(String response) throws JSONException {
        JSONObject userJson = new JSONObject(response);
        String id = authModelClass.auth(userJson);
        return id;
    }
}