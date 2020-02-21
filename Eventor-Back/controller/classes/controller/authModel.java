package controller;
//JPA (Begin)
import javax.persistence.*;
import javax.transaction.*;
import javax.annotation.Resource;
//JPA (End)

import org.json.*;

import java.io.Serializable;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
public class authModel implements Serializable{
    
    @PersistenceUnit(unitName = "persAuth")
    private EntityManagerFactory entityManagerFactory;

    @Resource
    private UserTransaction userTransaction;

    public Integer auth(JSONObject user) throws JSONException {

        String login = user.getString("login");
        String password = user.getString("password");

        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        }
        catch (Exception e) {
            return 0;
        }
        try {
            userTransaction.begin();
            entityManager.joinTransaction();

            AuthUser AU = entityManager.createQuery("SELECT ap FROM AuthUser AS ap WHERE ap.login LIKE :login AND ap.password LIKE :password", AuthUser.class)
                    .setParameter("login", login)
                    .setParameter("password", password).
                    getSingleResult();
            userTransaction.commit();
            return AU.getId();
        }
        catch (Exception e) {
            return 0;
        }
    }


}
