package controller;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import java.io.Serializable;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
public class tokenModel implements Serializable{

    @PersistenceUnit(unitName = "persAuth")
    private EntityManagerFactory entityManagerFactory;

    @Resource
    private UserTransaction userTransaction;

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
        }
        catch (Exception e) {
            return e.toString() + "1";
        }
        try{
            userTransaction.begin();
            entityManager.joinTransaction();

            Tokens token = new Tokens();
            token.setId(id_user);
            token.setToken(readyToken);

            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-mm-dd");
            Date dateExpiresIn = new SimpleDateFormat("yyyy-mm-dd").parse(formatForDateNow.format(dateNow));
            token.setExpires_in(dateExpiresIn);

            entityManager.persist(token);
            userTransaction.commit();
            return readyToken;
        }
        catch(Exception e3){
            return null;
        }
    }
}
