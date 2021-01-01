package sample;

public class Visitor extends Person{

    private String driversId;
    private String visitDate;
    private int inmateVisited; //inmateId

    public Visitor(String firstName, String lastName,
                   String driversId, String visitDate, int inmateVisited) {
        super(firstName, lastName);
        this.driversId = driversId;
        this.visitDate = visitDate;
        this.inmateVisited = inmateVisited;
    }

    public String getDriversId() {
        return driversId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public int getInmateVisited() {
        return inmateVisited;
    }

    public void setInmateVisited(int inmateVisited) {
        this.inmateVisited = inmateVisited;
    }

    public void setDriversId(String driversId) {
        this.driversId = driversId;
    }

}
