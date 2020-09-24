package ejb;

import data.Manager;
import data.Person;

import javax.ejb.Local;
import java.util.List;

@Local
public interface superManagerInterface {

    public boolean create(String email,String name,String password, int flag);
    public boolean checkUserExists(String email);

}
