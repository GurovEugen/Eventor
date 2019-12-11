import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import javax.inject.Named;
// or import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
// or import javax.faces.bean.SessionScoped;

//DB Pool (Begin)
import java.sql.*;
import javax.annotation.Resource;
import javax.sql.DataSource;

import javax.naming.InitialContext;
//DB Pool (End)

//JPA (Begin)
import javax.persistence.*;
import javax.transaction.*;
//JPA (End)

@Named("user")
@SessionScoped
public class authModel implements Serializable{

    @PersistenceUnit(unitName = "persUnitAuth")
    private EntityManagerFactory entityManagerFactory;

    @Resource
    private UserTransaction userTransaction;

    public String auth(JSONObject user) throws JSONException {

        String login = user.getString("login");

        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        }
        catch (Exception e) {
            String res = "EntityManager: " + e.getMessage();
            return res;
        }
        /*try {

            userTransaction.begin();
            entityManager.joinTransaction();
            Integer id = entityManager.find(authOperations.class, login).getId();
            userTransaction.commit();
            return id;
        }
        catch (Exception e) {
            return 0;
        }*/
    return "123";}
}
