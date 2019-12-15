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
@Path("/user")
public class UserController implements Serializable {


    @Inject
    private SelectedUser SU;

    @Inject
    private User user;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@QueryParam("id") Integer id) {

        return SU.getUserById(id);
    }



    @POST
    public String editUser(String editingUser) throws JSONException {

        JSONObject editingUs = new JSONObject(editingUser);

        return SU.editUser(editingUs);

    }
}