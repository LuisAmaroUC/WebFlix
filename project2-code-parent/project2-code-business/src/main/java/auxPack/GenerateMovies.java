package auxPack;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean

//Chamada aquando o deploy da aplicação para gerar filmes
public class GenerateMovies{

    @EJB
    private ejb.movieEJBInterface movieEJBInterface;

    public  void generateMovies(String title, double length, String type, String director,int year){
        movieEJBInterface.createMovie(title,length,type,director,year);
    }

}
