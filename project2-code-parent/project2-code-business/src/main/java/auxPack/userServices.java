package auxPack;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import DataTransferObjects.EJBtoDTO;
import DataTransferObjects.movieDTO;
import data.Movie;
import ejb.userEJBInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class userServices {

   @EJB
    private userEJBInterface userEJBInterface;



    public boolean create(String email,String name,String password,int ccNumber, Date dataDeInsc) {
        hashPassword hash = new hashPassword();
        String passwordHash = hash.getPasswordHash(password);
        return userEJBInterface.create(email,name, passwordHash,ccNumber,dataDeInsc);
    }

    public List<movieDTO> getWatchList(String email){

        EJBtoDTO entityToLogical  = new EJBtoDTO();
        List<Movie> movies = userEJBInterface.getWatchList(email);
        List<movieDTO> moviesDTO = new ArrayList<>();

        for(Movie movie : movies){
            moviesDTO.add(entityToLogical.createMovieDTO(movie));
        }

        return moviesDTO;
    }

    public boolean edit( String email,String name, int ccNumber){
        return userEJBInterface.edit(email,name,ccNumber);
    }

    public boolean deleteUser(String email){
        return userEJBInterface.delete(email);
    }

    public boolean addMovies(int id,String email){
        return userEJBInterface.addMovies(id,email);
    }

    public boolean checkType(String email){
        return userEJBInterface.checkTypes(email);
    }

    public void payment(){userEJBInterface.payment();}




}
