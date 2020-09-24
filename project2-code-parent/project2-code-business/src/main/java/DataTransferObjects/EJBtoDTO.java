package DataTransferObjects;
import data.Manager;
import data.Movie;
import data.Utilizador;


public class EJBtoDTO {


    public movieDTO createMovieDTO(Movie ejbMovie){
        movieDTO movie = new movieDTO();

        movie.setId(ejbMovie.getId());
        movie.setLength(ejbMovie.getLength());
        movie.setTitle(ejbMovie.getTitle());
        movie.setType(ejbMovie.getType());
        movie.setDirector(ejbMovie.getDirector());
        movie.setYear(ejbMovie.getYear());

        return movie;
    }


    public managerDTO createManagerDTO(Manager ejbManager){
        managerDTO user = new managerDTO();

        user.setCidadaoNumber(ejbManager.getCidadaoNumber());
        user.setEmail(ejbManager.getEmail());
        user.setName(ejbManager.getName());
        user.setPassword(ejbManager.getPassword());

        return user;

    }
    public utilizadorDTO createUtilizadorDTO(Utilizador ejbUtilizador){
        utilizadorDTO user = new utilizadorDTO();

        user.setCreditNumber(ejbUtilizador.getCreditNumber());
        user.setEmail(ejbUtilizador.getEmail());
        user.setName(ejbUtilizador.getName());
        user.setPassword(ejbUtilizador.getPassword());

        return user;

    }

}
