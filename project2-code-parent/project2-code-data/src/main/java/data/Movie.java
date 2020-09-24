package data;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String title;
    private double length;
    private String type;
    private String director;
    private int year;
    @ManyToMany(cascade = CascadeType.REFRESH)
    private List<Person> usersList = new ArrayList<>();
    //REFRESH is to refresh the data in the object. Perhaps there was a change on the database which needs to be synced. (sync from database)

    public Movie(String title, double length, String type,String director, int year) {
        this.title = title;
        this.length = length;
        this.type = type;
        this.director = director;
        this.year = year;
    }
    public Movie() {
        super();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Person> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Person> usersList) {
        this.usersList = usersList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", length=" + length +
                ", type='" + type + '\'' +
                '}';
    }
}
