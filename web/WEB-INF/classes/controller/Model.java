//JPA (Begin)
import javax.persistence.*;
import javax.transaction.*;
import javax.annotation.Resource;
//JPA (End)   

import org.json.*;

import java.io.Serializable;

import java.util.List;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@Named("user") // or @ManagedBean(name="user")
@SessionScoped
public class Model implements Serializable {

  //ПЕРЕМЕННЫЕ
  @PersistenceUnit(unitName = "persAuth")
  private EntityManagerFactory entityManagerFactory;

  @Resource
  private UserTransaction userTransaction;
   
   //отправляю жсон со списком всех событий
   public List <Event> getEvents() {

       String res="";
       List <Event> eventList = null;
       EntityManager entityManager;
       entityManager = entityManagerFactory.createEntityManager();
       try {
           //
           userTransaction.begin();
           entityManager.joinTransaction();
           Query query = entityManager.createQuery("select e from Event e", Event.class);
           eventList = (List <Event>) query.getResultList();
           userTransaction.commit();
           return eventList;
       }
           catch (Exception e) {
           res = res + e.getMessage();
           return null;
       }
    }


    public List <Group> getGroups() {

        String res="";
        List <Group> groupList = null;
        EntityManager entityManager;
        entityManager = entityManagerFactory.createEntityManager();
        try {
            //
            userTransaction.begin();
            entityManager.joinTransaction();
            res="1";
            Query query = entityManager.createQuery("select g from Group g", Group.class);
            res = "2";
            groupList = (List <Group>) query.getResultList();
            res = "3";
            userTransaction.commit();
        }
        catch (Exception e) {
            res = res + e.getMessage();

        }
        return groupList;

    }


    //принимаю жсон с событием, добавляю в базу                                   
    public String setNewEvent(JSONObject newEvent) {
        String res = "OK";
        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();

            userTransaction.begin();
            entityManager.joinTransaction();

            Event eventToDb = new Event();

            eventToDb.setName(newEvent.getString("name"));
            eventToDb.setInfo(newEvent.getString("info"));
            eventToDb.setPlace(newEvent.getString("place"));
            eventToDb.setGroupId(newEvent.getInt("group_id"));
            eventToDb.setUserId(newEvent.getInt("user_id"));
            eventToDb.setStartDate(newEvent.getString("startDate"));
            eventToDb.setEndDate(newEvent.getString("endDate"));
            entityManager.persist(eventToDb);
            userTransaction.commit();
        }
        catch (Exception e){
            res = "Nichego ne ok2 " + e.getMessage();
        }

        return res;
    }



}