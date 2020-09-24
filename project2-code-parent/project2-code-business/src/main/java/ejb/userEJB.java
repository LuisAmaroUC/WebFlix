package ejb;


import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Stateless;
import javax.jms.MessageProducer;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Stateless

public class userEJB implements userEJBInterface{


    static Logger logger = Logger.getLogger(userEJB.class);
    @PersistenceContext(name="User")
    EntityManager em;

    public boolean create(String email, String name, String password, int ccNumber, Date dataDeInsc) {
      try {

            if(checkUserExists(email)) {
                Person newUser = new Utilizador(email,name, password, ccNumber,dataDeInsc);
                em.persist(newUser);

                return true;
            }else{
                return false;
            }

         }catch(Exception e) {
           logger.fatal("Excpetion createUser",e);
            return false;
        }


    }
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
    public boolean edit( String email,String name, int ccNumber){
        try{
            //em.getTransaction().begin();
            Person user = em.find(Utilizador.class,email);
            if(!name.equals("default"))user.setName(name);
            if(ccNumber != 0)((Utilizador) user).setCreditNumber(ccNumber);
            //logger.info(ccNumber);
            em.persist(user);
           // em.getTransaction().commit();
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    public boolean delete(String email){
            try{
                Person user = em.find(Person.class,email);

                List<Movie> moviesList = user.getWatchList();

                for(Movie movie : moviesList){
                    //REMOVER O USER DA LISTA DE CADA FILME
                    Movie movie1 = em.find(Movie.class, movie.getId());
                    List <Person> userList = movie1.getUsersList();
                    userList.remove(user);
                    movie.setUsersList(userList);
                    em.persist(movie);


                }
                em.remove(user);
                return true;
            }catch(Exception e) {
                logger.fatal(e);
                return false;
            }
    }
    public boolean addMovies(int id,String email){
        logger.info(id);
        logger.info(email);
        try{
            Movie movie = em.find(Movie.class,id);
            Person user = em.find(Person.class,email);

            List<Movie> lists = user.getWatchList();
            List<Person> listUser = movie.getUsersList();

            //CHECK IF MOVIE WAS ALREADY ADDED
            if(!lists.contains(movie)) {

                lists.add(movie);
                listUser.add(user);

                user.setWatchList(lists);
                movie.setUsersList(listUser);

                return true;
            }else return false;
        }catch(Exception e) {
            return false;
        }
    }

    public List<Movie> getWatchList(String email){
        List<Movie> userMovies = new ArrayList<>();
        try{
            Person user = em.find(Person.class,email);
            userMovies = user.getWatchList();

            return userMovies;

        }catch(Exception e) {
            return userMovies;
        }


    }


    public boolean checkTypes(String email){
        try{
            Person user = em.find(Person.class, email);
            if (user.getClass().equals(Manager.class)) return true;
            else return false;

        }catch(Exception e) {
            logger.fatal("checkType"+e);
            return false;
        }
    }


    //Runs every 2 minutes
    //get all users
    //go changing flag to par/impar users to pay
    //send an email every time payment goes through
    //sucessufull or not
    @Schedules({
            @Schedule(hour = "*", minute = "*/2")
    })
    public void payment(){
        List<Person> users = new ArrayList<>();

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        Date dNow = new Date( );

        try{
            Query query = em.createQuery("SELECT p FROM Person p");
            users = query.getResultList();

            for(Person user : users){
                if(user.getClass().equals(Utilizador.class)){
                    Utilizador newUser = (Utilizador) user;
                    cal1.setTime(newUser.getDataDeInsc());
                    cal2.setTime(dNow);

                    boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                                      cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

                    //se for o mesmo dia sendMail
                    if(sameDay){
                        logger.info("SAME DAY");
                        sendEmail("amaroret@hotmail.com","teste","ola");
                    }else logger.info("NOT IN THE SAME DAY");
                }
            }

        }catch(Exception e){
            logger.fatal("Payment"+e);

        }


    }
    @Resource(name = "java:jboss/mail/Gmail")
    private Session session;
    public void sendEmail(String to, String subject, String body){
        try{
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            logger.warn("Cannot send email",e);

        }
    }










}
