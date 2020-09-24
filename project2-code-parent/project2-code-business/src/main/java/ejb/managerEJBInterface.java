package ejb;

import data.Manager;

import javax.ejb.Local;
import java.util.List;

@Local
public interface managerEJBInterface {

    public boolean removeMoviesWatchList(int id, String email);
    public boolean edit( String email,String name, String ccNumber);
    public boolean checkUserExists(String email);
    public boolean createManager(String email,String name,String password, String cidadaoNumber);
    public boolean removeManager(String email);
    public List<Manager> getAllManagers();
    public boolean checkManagerType(String email);
}
