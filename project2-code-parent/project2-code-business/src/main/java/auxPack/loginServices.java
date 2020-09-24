package auxPack;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ejb.loginEJBInterface;

@Stateless
@LocalBean
//Usada para autenticar todos os logins da aplicação
public class loginServices {

    @EJB
    private loginEJBInterface loginEJBInterface;

    public boolean login(String email,String password) {
        return loginEJBInterface.authentication(email,password);
    }
}

