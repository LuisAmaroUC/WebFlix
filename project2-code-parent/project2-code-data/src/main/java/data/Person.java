package data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person implements Serializable{
        private static final long serialVersionUID = 1L;

        @Id
        private String email;
        private String name;
        private String password;
        @ManyToMany(cascade = CascadeType.REFRESH)
        private List<Movie> watchList = new ArrayList<>();

        //REFRESH is to refresh the data in the object. Perhaps there was a change on the database which needs to be synced. (sync from database)

    public Person() {
        super();
    }

    public Person(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public List<Movie> getWatchList() {
        return watchList;
    }

    public void setWatchList(List<Movie> watchList) {
        this.watchList = watchList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
