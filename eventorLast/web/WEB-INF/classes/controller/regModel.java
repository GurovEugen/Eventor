//JPA (Begin)
import javax.persistence.*;
import javax.transaction.*;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
//JPA (End)

import org.json.*;

import java.io.Serializable;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
public class regModel implements Serializable {

    @PersistenceUnit(unitName = "persAuth")
    private EntityManagerFactory entityManagerFactory;

    @Inject
    private AuthUser AU;

    @Resource
    private UserTransaction userTransaction;

    public String reg(JSONObject user) throws JSONException {

        Integer lastId;
        //AuthUser AU;

        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        }
        catch (Exception e) {
            return "Произошла ошибка " + e.getMessage();
        }
        try {
            userTransaction.begin();
            entityManager.joinTransaction();
                     AU = entityManager.createQuery("SELECT ap FROM AuthUser AS ap WHERE ap.login LIKE :login", AuthUser.class)
                    .setParameter("login", user.getString("login"))
                    .getSingleResult();
                     return "Указанный пользователь уже зарегистрирован в системе.";
        }
        catch (NoResultException e){
                try {
                    entityManager.joinTransaction();

                    Date dateOfBirth = new SimpleDateFormat("yyyy-mm-dd").parse(user.getString("dateOfBirth"));

                    UserProfile newUser = new UserProfile();
                    newUser.setFirstName(user.getString("name"));
                    newUser.setLastName(user.getString("surname"));
                    newUser.setDateOfBirth(dateOfBirth);
                    newUser.setGender(user.getString("gender"));
                    newUser.setBio(user.getString("bio"));

                    entityManager.persist(newUser);
                    lastId = newUser.getId();
                }
                catch (Exception e2) {
                    return "Произошла ошибка при регистрации пользователя";
                }
                try{
                    entityManager.joinTransaction();

                    AuthUser newAuthUser = new AuthUser();
                    newAuthUser.setId(lastId);
                    newAuthUser.setLogin(user.getString("login"));
                    newAuthUser.setPassword(user.getString("password"));

                    entityManager.persist(newAuthUser);
                    userTransaction.commit();
                    return "";
                }
                catch(Exception e3){
                    return "Произошла ошибка при регистрации пользователя.";
                }
        }
        catch (NotSupportedException e) {
            return "Произошла ошибка";
        }
        catch (SystemException e) {
            return "Произошла ошибка";
        }
    }
}
