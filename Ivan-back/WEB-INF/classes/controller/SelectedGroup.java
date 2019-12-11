import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.*;
import javax.ws.rs.core.MediaType;

public class SelectedGroup implements Serializable {

        @Inject
        private Group groupFind;

        @Resource
        private UserTransaction userTransaction;

        @PersistenceUnit(unitName = "groupPersistenceUnit")
        private EntityManagerFactory entityManagerFactory;

        public Group getGroupById(Integer id) {
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
                groupFind = entityManager.find(Group.class,id);
                userTransaction.commit();
            }
            catch (Exception e) {
                res = "JPA: " + e.getMessage();
            }
            return groupFind;
        }
    }


