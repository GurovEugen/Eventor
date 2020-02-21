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

@SessionScoped
@Path("/controller")
public class Controller implements Serializable {
    @Inject
    private authModel authModelClass;
    @Inject
    private regModel regModelClass;

    @Path("/reg")
    @POST
    public String regControllerFunc(String response) throws JSONException {
        JSONObject userJson = new JSONObject(response);
        String id = regModelClass.reg(userJson);
        return id;
    }

    @Path("/auth")
    @POST
    public Integer authControllerFunc(String response) throws JSONException {
        JSONObject userJson = new JSONObject(response);
        Integer id = authModelClass.auth(userJson);
        return id;
        //Тут id уже есть, можно добавить tokens
    }
}