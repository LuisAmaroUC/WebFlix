package DataTransferObjects;

public class utilizadorDTO extends personDTO{
    private int creditNumber;

    public utilizadorDTO(){
        super();
    }
    public utilizadorDTO(String email, String name, String password, int creditNumber){
        super(email,name,password);
        this.creditNumber = creditNumber;
    }

    public int getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(int creditNumber) {
        this.creditNumber = creditNumber;
    }
}
