package data;


import javax.persistence.Entity;
import java.util.Date;
import java.util.List;
@Entity
public class Utilizador extends Person{

    private int creditNumber;
    private Date dataDeInsc;

    public Utilizador(){
        super();
    }

    public Utilizador(String email, String name, String password, int creditNumber,Date dataDeInsc){
        super(email,name,password);
        this.creditNumber = creditNumber;
        this.dataDeInsc = dataDeInsc;
    }

    public int getCreditNumber() {

        return creditNumber;
    }

    public void setCreditNumber(int creditNumber) {

        this.creditNumber = creditNumber;
    }

    public Date getDataDeInsc() {
        return dataDeInsc;
    }

    public void setDataDeInsc(Date dataDeInsc) {
        this.dataDeInsc = dataDeInsc;
    }
}
