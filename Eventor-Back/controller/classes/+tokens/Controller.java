package controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

@SessionScoped
@Path("/controller")
public class Controller implements Serializable {
    @Inject
    private authModel authModelClass;
    @Inject
    private regModel regModelClass;
    @Inject
    private tokenModel tokenModelClass;

    @Path("/reg")
    @POST
    public String regControllerFunc(String response) throws JSONException {
        JSONObject userJson = new JSONObject(response);
        String id = regModelClass.reg(userJson);
        return id;
    }

    @Path("/auth")
    @POST
    public JSONObject authControllerFunc(String response) throws JSONException, NoSuchAlgorithmException {
        JSONObject userJson = new JSONObject(response);

        Integer success = 0;

        Integer id = authModelClass.auth(userJson);
        if (id != 0){
            String token = tokenModelClass.setToken(id);
            if(token != null) {
                JSONObject idAndToken = new JSONObject();
                idAndToken.put("id_user", id);
                idAndToken.put("token", token);
                return idAndToken;
            }
        }
    return null;
    }

}