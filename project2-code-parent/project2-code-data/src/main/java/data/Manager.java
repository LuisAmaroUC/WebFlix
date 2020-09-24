package data;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Manager extends Person{

    private String cidadaoNumber;

    public Manager(){
        super();
    }

    public Manager(String email, String name, String password, String cidadaoNumber){
        super(email,name,password);
        this.cidadaoNumber = cidadaoNumber;
    }

    public String getCidadaoNumber() {
        return cidadaoNumber;
    }

    public void setCidadaoNumber(String cidadaoNumber) {
        this.cidadaoNumber = cidadaoNumber;
    }
}
