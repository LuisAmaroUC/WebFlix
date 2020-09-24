package DataTransferObjects;

public class managerDTO extends personDTO{
    private String cidadaoNumber;

    public managerDTO(){
        super();
    }
    public managerDTO(String email, String name, String password, String cidadaoNumber){
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
