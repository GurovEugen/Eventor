import org.json.JSONObject;

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

public class SelectedUser implements Serializable{

    @Inject
    private User userFind;
    @Inject
    private User editingUser;
    @Resource
    private UserTransaction userTransaction;

    @PersistenceUnit(unitName = "userPersistenceUnit")
    private EntityManagerFactory entityManagerFactory;

    public User getUserById(Integer id) {
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
            userFind = entityManager.find(User.class,id);
            userTransaction.commit();
        }
        catch (Exception e) {
            res = "JPA: " + e.getMessage();
        }
        return userFind;
    }

    public String editUser(JSONObject User) {
        String res = "OK";
        EntityManager entityManager;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            editingUser = entityManager.find(User.class,User.getInt("id"));
            editingUser.setFirstName(User.getString("firstName"));
            editingUser.setLastName(User.getString("lastName"));
            editingUser.setBirthDate(User.getString("birthDate"));
            editingUser.setBio(User.getString("bio"));
            editingUser.setGender(User.getString("gender"));
            entityManager.merge(editingUser);
            userTransaction.commit();
        }
        catch (Exception e){
            res += "not ok" + e.getMessage();
        }

        return res;
    }
}
