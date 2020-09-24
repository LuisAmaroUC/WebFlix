package ejb;
import data.Movie;

import javax.ejb.Local;
import java.util.List;

@Local
public interface movieEJBInterface {
    public boolean createMovie(String title, double length, String type, String director,int year);
    public List<Movie> printAll();
    public List<Movie> getRandomContent();
    public boolean editMovie(String title,double length, String type, String director,int id, int year);
    public boolean delete(int id);
    public List<Movie> printByCategory(String type, int flag);
    public List<Movie> printByDirector(String director, int flag);
    public List<Movie> printByRangerOfYears(int year1, int year2, int flag);
    public List<Movie> getAllContent(int flag);

}
