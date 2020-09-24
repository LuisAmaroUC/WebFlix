package data;

import javax.persistence.Entity;
import java.util.List;
@Entity
public class SuperManager extends  Person{
    private int flag;


    public SuperManager(){
        super();
    }
    public SuperManager(String email, String name, String password, int flag){
        super(email,name,password);
        this.flag = flag;
    }
}
