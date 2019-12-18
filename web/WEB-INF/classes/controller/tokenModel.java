//JPA (Begin)
import javax.persistence.*;
import javax.transaction.*;
import javax.annotation.Resource;
//JPA (End)

import java.nio.charset.StandardCharsets;
import java.security.*;
import org.json.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import java.io.Serializable;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.transaction.RollbackException;

@SessionScoped
public class tokenModel implements Serializable {

    @PersistenceUnit(unitName = "persAuth")
    private EntityManagerFactory entityManagerFactory;

    @Resource
    private UserTransaction userTransaction;

    @Inject
    private Tokens tokenI;

    public String setToken(Integer id_user) throws JSONException, NoSuchAlgorithmException {

        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        String todayAsString = df.format(today);

        final Random random = new Random();
        String tokenIn = todayAsString + String.valueOf(random.nextInt(10)) + id_user.toString();

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(tokenIn.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        String readyToken = sb.toString();

        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            return e.getMessage();
        }
        try {
            userTransaction.begin();
            entityManager.joinTransaction();

            //Tokens token = new Tokens();
            tokenI.setId(id_user);
            tokenI.setToken(readyToken);

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 2);
            Date tommrrow = cal.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(tommrrow);
            Date result = formatter.parse(date);
            tokenI.setExpires_in(result);

            entityManager.persist(tokenI);
            userTransaction.commit();
            return readyToken;
        } catch (Exception e3) {
            return "";
        }
    }

    public Boolean tokenRemove(JSONObject user) throws JSONException {

        Integer id = user.getInt("id");
        String token = user.getString("token");

        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            return false;
        }
        try {
            userTransaction.begin();
            entityManager.joinTransaction();

            /*Tokens tokenUser = entityManager.createQuery("SELECT t FROM Tokens AS t WHERE t.id_user = :id_user AND t.token LIKE :token", Tokens.class)
                    .setParameter("id_user", id)
                    .setParameter("token", token).
                            getSingleResult();*/
            tokenI = entityManager.createQuery("SELECT t FROM Tokens AS t WHERE t.id_user = :id_user AND t.token LIKE :token", Tokens.class)
                    .setParameter("id_user", id)
                    .setParameter("token", token).
                            getSingleResult();
            entityManager.remove(tokenI);
            userTransaction.commit();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Boolean tokenAccess(JSONObject user) throws JSONException {

        Integer id = user.getInt("id");
        String token = user.getString("token");

        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        }
        catch (Exception e) {
            return false;
            //e.toString()
        }
        try {
            userTransaction.begin();
            entityManager.joinTransaction();

            /*Tokens tokenUser = entityManager.createQuery("SELECT t FROM Tokens AS t WHERE t.id_user = :user_id AND t.token LIKE :token", Tokens.class)
                    .setParameter("user_id", id)
                    .setParameter("token", token).
                            getSingleResult();*/
            tokenI = entityManager.createQuery("SELECT t FROM Tokens AS t WHERE t.id_user = :user_id AND t.token LIKE :token", Tokens.class)
                    .setParameter("user_id", id)
                    .setParameter("token", token).
                            getSingleResult();

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 2);
            Date tommrrow = cal.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(tommrrow);
            Date result = formatter.parse(date);
            /*tokenUser.setExpires_in(result);
            entityManager.merge(tokenUser);*/
            tokenI.setExpires_in(result);
            entityManager.merge(tokenI);
            userTransaction.commit();

            return true;
            /*Date now = new Date();
            if (tokenUser.getExpires_in().before(now)) {
                entityManager.remove(tokenUser);
                userTransaction.commit();
                return false;
                //return "Токен истек";
            } else {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, 2);
                Date tommrrow = cal.getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String date = formatter.format(tommrrow);
                Date result = formatter.parse(date);
                tokenUser.setExpires_in(result);
                entityManager.merge(tokenUser);
                userTransaction.commit();
                return true;
            }*/
        }
        catch (Exception e) {
            return false;
            //return e.toString();
        }
    }

}