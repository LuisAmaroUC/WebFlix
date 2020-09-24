package auxPack;

import ejb.superManagerInterface;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


@Stateless
@LocalBean
public class superManagerServices {

    @EJB
    superManagerInterface superManager;



    //METODO PARA CRIAR UTILIZADOR PRINCIPAL
    public boolean create(String email,String name,String password, int flag) {
        hashPassword hash = new hashPassword();
        String passwordHash = hash.getPasswordHash(password);
        return superManager.create(email, name,passwordHash,flag);
    }



}
