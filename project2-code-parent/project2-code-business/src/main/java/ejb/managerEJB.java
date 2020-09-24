package ejb;

import data.Manager;
import data.Movie;
import data.Person;
import data.SuperManager;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class managerEJB implements managerEJBInterface {
    static Logger logger = Logger.getLogger(userEJB.class);
    @PersistenceContext(name="Manager")
    EntityManager em;


    public boolean checkUserExists(String email){
        try{

            Query query = em.createQuery("SELECT p FROM Person p WHERE p.email = :email");
            List result = query.setParameter("email", email).getResultList();

            if(result.size() > 0) {
                return false;
            }
            else {
                return true;}

        }catch(Exception e){
            logger.fatal("Excpetion userExists",e);
            return false;
        }
    }


    public boolean removeMoviesWatchList(int id, String email){

        try{
            Person user = em.find(Person.class, email);
            Movie movie1 = em.find(Movie.class,id);

            List<Movie> userMovies = user.getWatchList();
            List<Person> moviesUser = movie1.getUsersList();

            for(Movie movie : userMovies){
                if(movie.getId() == id) {
                    userMovies.remove(movie);
                    moviesUser.remove(user);
                    user.setWatchList(userMovies);
                    movie1.setUsersList(moviesUser);
                    return true;
                }
            }

            return false;

        }catch(Exception e) {
            return false;
        }
    }
    public boolean edit( String email,String name, String ccNumber){
        try{

            Person user = em.find(Manager.class,email);

            if(!name.equals("default"))user.setName(name);
            if(ccNumber.equals("0"))((Manager) user).setCidadaoNumber(ccNumber);


            em.persist(user);

            return true;
        }catch(Exception e) {
            return false;
        }
    }
    public boolean createManager(String email,String name,String password, String cidadaoNumber) {
        try {

            if (checkUserExists(email)) {
                Person newUser = new Manager(email,name,password,cidadaoNumber);
                em.persist(newUser);
                return true;

            } else {
                return false;
            }

        } catch (Exception e) {

            return false;
        }

    }


    public boolean removeManager(String email){
        try{
            Person user = em.find(Person.class,email);

            List<Movie> moviesList = user.getWatchList();
            List <Person> userList = new ArrayList<>();

            for(Movie movie : moviesList){

                Movie movieAux = em.find(Movie.class, movie.getId());

                userList = movieAux.getUsersList();
                userList.remove(user);

                movie.setUsersList(userList);

                em.persist(movie);
            }

            em.remove(user);

            return true;

        }catch(Exception e) {

            return false;
        }
    }

    public List<Manager> getAllManagers(){

        List<Manager> users = new ArrayList<>();
        try{
            Query query = em.createQuery("Select us from Person us where us.class in :class");
            users = query.setParameter("class", "Manager").getResultList();

            return users;

        }catch(Exception e) {
            logger.fatal(e);
            return users;
        }
    }
    public boolean checkManagerType(String email){
        try{
            Person user = em.find(Person.class, email);
            if (user.getClass().equals(SuperManager.class))
            {
                return true;
            }
            else {
                return false;
            }

        }catch(Exception e) {
            logger.fatal("checkType"+e);
            return false;
        }
    }
}
