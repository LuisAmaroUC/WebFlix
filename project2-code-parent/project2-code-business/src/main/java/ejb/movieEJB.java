package ejb;

import DataTransferObjects.EJBtoDTO;
import DataTransferObjects.movieDTO;
import data.Movie;
import data.Person;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class movieEJB implements  movieEJBInterface{

    @PersistenceContext(name="Movie")
    EntityManager em;
    static Logger logger = Logger.getLogger(movieEJB.class);


    public boolean createMovie(String title, double length, String type, String director, int year){
        try {

            Movie newMovie = new Movie(title,length,type,director,year);
            em.persist(newMovie);

            return true;
        }catch(Exception e) {
            return false;
        }
    }


    public List<Movie> printAll(){

        List<Movie> movies = new ArrayList<>();
        try{
            Query query = em.createQuery("Select mv from Movie mv order by mv.type");
            movies = query.getResultList();
            return movies;
        }catch(Exception e) {
            return movies;
        }

    }
    //REQ.14 SHOW ALL CONTENT
    public List<Movie> getAllContent(int flag){

        List<Movie> movies = new ArrayList<>();
        Query query = null;
        try{
            if(flag == 0) {
                query = em.createQuery("Select mv from Movie mv order by mv.type ASC");
            }else{
                query = em.createQuery("Select mv from Movie mv order by mv.type DESC");
            }
            movies = query.getResultList();
            return movies;
        }catch(Exception e) {
            return movies;
        }

    }
    //REQ.15 SHOW ALL CONTENT OF A GIVEN CATEGORY
    public List<Movie> printByCategory(String type, int flag){

        List<Movie> movies = new ArrayList<>();
        Query query = null;
        try{
            //FLAG = 0 ASC ORDER
            if(flag == 0) {
                query = em.createQuery("Select mv from Movie mv where mv.type = :type order by mv.title ASC");
            }else{//FLAG = 1 DESC ORDER
                query = em.createQuery("Select mv from Movie mv  where mv.type = :type order by mv.title DESC");
            }
            movies = query.setParameter("type", type).getResultList();

            return movies;
        }catch(Exception e) {
            return movies;
        }

    }
    //REQ.16 SHOW ALL CONTENT OF A GIVEN Director
    public List<Movie> printByDirector(String director, int flag){

        List<Movie> movies = new ArrayList<>();
        Query query = null;
        try{
            //FLAG = 0 ASC ORDER
            if(flag == 0) {
                query = em.createQuery("Select mv from Movie mv where mv.director = :director order by mv.type ASC");
            }else{//FLAG = 1 DESC ORDER
                query = em.createQuery("Select mv from Movie mv where mv.director = :director order by mv.type DESC");
            }
            movies = query.setParameter("director", director).getResultList();

            return movies;
        }catch(Exception e) {
            return movies;
        }

    }

    //REQ.17 SHOW ALL CONTENT WITHIN A RANGE OF YEARS
    public List<Movie> printByRangerOfYears(int year1, int year2, int flag){

        List<Movie> movies = new ArrayList<>();
        Query query = null;
        try{
            //FLAG = 0 ASC ORDER
            if(flag == 0) {
                query = em.createQuery("Select mv from Movie mv where mv.year BETWEEN :ano1 AND  :ano2 order by mv.type ASC");
            }else{//FLAG = 1 DESC ORDER
                query = em.createQuery("Select mv from Movie mv where  mv.year BETWEEN :ano1 AND  :ano2 order by mv.type DESC");
            }
            movies = query.setParameter("ano1",year1).setParameter("ano2",year2).getResultList();


            return movies;
        }catch(Exception e) {
            return movies;
        }

    }

    public List<Movie> getRandomContent(){
        //Random gerador = new Random();

        //Query query = em.createQuery("select m1.id from Movie m1 where (m1.type,m1.id) IN (select Distinct m2.type, m2.id from Movie m2 group By m2.type)");
        /*for(int i = 0; i< query.getResultList().size();i++){
            logger.info(query.getResultList().get(i));
          movies.add(em.find(Movie.class,query.getResultList().get(i)));
        }*/
        List<Movie> movies = new ArrayList<>();
        try {
            Query query = em.createQuery("Select mv from Movie mv group by mv.type");
            movies = query.getResultList();
            return movies;
        }catch (Exception e){
            return movies;
        }
    }
    public boolean editMovie(String title,double length, String type, String director,int id,int year) {
        try {

            Movie movie = em.find(Movie.class,id);

            if(!title.equals("default"))movie.setTitle(title);
            if(length != 0)movie.setLength(length);
            if(!type.equals("default"))movie.setType(type);
            if(!director.equals("default"))movie.setDirector(director);
            if(year !=0)movie.setYear(year);

            em.persist(movie);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean delete(int id){
        try{
            Movie movie = em.find(Movie.class,id);

            List<Person> userList = new ArrayList<>();
            List<Movie> moviesList = new ArrayList<>();

            userList = movie.getUsersList();

            for(Person userF : userList){
                Person user = em.find(Person.class,userF.getEmail());
                moviesList = user.getWatchList();
                moviesList.remove(movie);
                user.setWatchList(moviesList);
            }

            em.remove(movie);
            return true;
        }catch(Exception e) {
            return false;
        }
    }
}
