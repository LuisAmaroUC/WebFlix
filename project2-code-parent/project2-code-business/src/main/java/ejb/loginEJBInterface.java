package ejb;

import javax.ejb.Local;

@Local

public interface loginEJBInterface {
    public boolean authentication(String email,String password);
}
