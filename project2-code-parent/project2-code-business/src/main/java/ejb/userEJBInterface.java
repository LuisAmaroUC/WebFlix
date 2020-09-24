package ejb;

import data.Movie;
import javax.ejb.Local;
import java.util.Date;
import java.util.List;


@Local

public interface userEJBInterface {


    public boolean create(String email,String name,String password,int ccNumber, Date dataDeInsc);
    public boolean edit( String email,String name, int ccNumber);
    public boolean delete(String email);
    public boolean addMovies(int id,String email);
    public List<Movie> getWatchList(String email);
    public boolean checkTypes(String email);
    public void payment();
    public void sendEmail(String to, String subject, String body);




}

