package auxPack;

import DataTransferObjects.EJBtoDTO;
import DataTransferObjects.movieDTO;
import data.Movie;
import ejb.movieEJBInterface;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
@Stateless
@LocalBean
//Usada para todos os metodos relacionados com Movies
public class movieS {

    @EJB
    private movieEJBInterface movieEJBInterface;

    //CRUD DA APLICAÇÃO
    public boolean create(String title, String type, double length, String director,int year){
        return movieEJBInterface.createMovie(title,length,type, director,year);
    }
    public boolean edit(String title, String type, double length, String director, int id,int year){
        return movieEJBInterface.editMovie(title,length,type,director, id,year);
    }
    public boolean delete(int id){
        return movieEJBInterface.delete(id);
    }

    //METODOS COM DTO
    public List<movieDTO> getRandomContent(){

        EJBtoDTO entityToLogical  = new EJBtoDTO();

        List<Movie> movies = movieEJBInterface.getRandomContent();
        List<movieDTO> moviesDTO = new ArrayList<>();

        for(Movie movie : movies){
            moviesDTO.add(entityToLogical.createMovieDTO(movie));
        }

        return moviesDTO;
    }
    public List<movieDTO> printByCategory(String type, int flag){

        EJBtoDTO entityToLogical  = new EJBtoDTO();

        List<Movie> movies = movieEJBInterface.printByCategory(type,flag);
        List<movieDTO> moviesDTO = new ArrayList<>();

        for(Movie movie : movies){
            moviesDTO.add(entityToLogical.createMovieDTO(movie));
        }

        return moviesDTO;
    }
    public List<movieDTO> printByDirector(String director, int flag){

        EJBtoDTO entityToLogical  = new EJBtoDTO();

        List<Movie> movies = movieEJBInterface.printByDirector(director,flag);
        List<movieDTO> moviesDTO = new ArrayList<>();

        for(Movie movie : movies){
            moviesDTO.add(entityToLogical.createMovieDTO(movie));
        }

        return moviesDTO;
    }
    public List<movieDTO> printByRangeOfYear(int year1,int year2, int flag){

        EJBtoDTO entityToLogical  = new EJBtoDTO();

        List<Movie> movies = movieEJBInterface.printByRangerOfYears(year1,year2,flag);
        List<movieDTO> moviesDTO = new ArrayList<>();

        for(Movie movie : movies){
            moviesDTO.add(entityToLogical.createMovieDTO(movie));
        }

        return moviesDTO;
    }

    public List<movieDTO> getAll(){
        EJBtoDTO entityToLogical = new EJBtoDTO();

        List<Movie> movies = movieEJBInterface.printAll();
        List <movieDTO> moviesDTO = new ArrayList<>();

        for(Movie movie : movies){
            moviesDTO.add(entityToLogical.createMovieDTO(movie));
        }

        return moviesDTO;

    }

    public List<movieDTO> getAllContent( int flag){
        EJBtoDTO entityToLogical = new EJBtoDTO();

        List<Movie> movies = movieEJBInterface.getAllContent(flag);
        List <movieDTO> moviesDTO = new ArrayList<>();

        for(Movie movie : movies){
            moviesDTO.add(entityToLogical.createMovieDTO(movie));
        }

        return moviesDTO;

    }


}
