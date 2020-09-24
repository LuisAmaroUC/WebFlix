package ejb;


import auxPack.hashPassword;
import data.Person;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless

public class loginEJBBean implements loginEJBInterface {


    @PersistenceContext(name = "Person")
    EntityManager em;



    public boolean authentication(String email, String password) {
        try {
            hashPassword hash = new hashPassword();
            Person user = em.find(Person.class, email);

            if (hash.getPasswordHash(password).equals(user.getPassword())) {
                return true;
            } else
                return false;
        } catch (Exception e){
            return false;
        }
    }
}



