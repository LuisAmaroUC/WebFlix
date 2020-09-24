package auxPack;

import DataTransferObjects.EJBtoDTO;
import DataTransferObjects.personDTO;
import data.Manager;
import ejb.managerEJBInterface;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
//Usada para chamar metodos exclusivos ao manager
public class managerServices {
    @EJB
    managerEJBInterface manager;

    public boolean removeMovies(int id, String email){ return manager.removeMoviesWatchList(id,email);}
    public boolean edit( String email,String name, String ccNumber){
        return manager.edit(email,name,ccNumber);
    }
    //METODO PARA CRIAR MANAGERS
    public boolean createManager(String email,String name,String password, String cidadaoNumber){
        hashPassword hash = new hashPassword();
        String passwordHash = hash.getPasswordHash(password);
        return manager.createManager( email,name,passwordHash,cidadaoNumber);

    }

    //METODO PARA APAGAR MANAGERS
    public boolean removeManager(String email){
        return manager.removeManager(email);
    }

    //METODO PARA VER TODOOS OS MANAGERS
    public List<personDTO> getAllManagers(){

        EJBtoDTO entityToLogical = new EJBtoDTO();

        List<Manager> users = manager.getAllManagers();
        List<personDTO> userDTO = new ArrayList<>();

        for(Manager user : users){
            userDTO.add(entityToLogical.createManagerDTO(user));
        }

        return userDTO;
    }

    public boolean checkManagerType(String email){
        return manager.checkManagerType(email);
    }
}
