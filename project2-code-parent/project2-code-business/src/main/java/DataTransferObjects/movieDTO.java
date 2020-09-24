package DataTransferObjects;

public class movieDTO {
    private int id;
    private String title;
    private double length;
    private String type;
    private int year;
    private String director;

    public movieDTO(){
        super();
    }
    public movieDTO(int id, String title, double length, String type, String director,int year) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.type = type;
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
}
