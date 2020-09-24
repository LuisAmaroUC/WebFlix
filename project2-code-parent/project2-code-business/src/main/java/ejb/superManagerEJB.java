package ejb;

import data.*;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class superManagerEJB implements superManagerInterface {
    static Logger logger = Logger.getLogger(userEJB.class);
    @PersistenceContext(name="SuperManager")
    EntityManager em;

    public boolean checkUserExists(String email){

        try{

            Query query = em.createQuery("SELECT p FROM Person p WHERE p.email = :email");
            List result = query.setParameter("email", email).getResultList();

            if(result.size() > 0) return false;
            else return true;

        }catch(Exception e){
            logger.fatal("Excpetion userExists",e);
            return false;
        }
    }

    public boolean create(String email,String name,String password, int flag) {
        try {

            if(checkUserExists(email)) {
                Person newUser = new SuperManager(email,name,password,flag);
                em.persist(newUser);
                return true;

            }else{
                return false;
            }

        }catch(Exception e) {

            return false;
        }


    }



}
