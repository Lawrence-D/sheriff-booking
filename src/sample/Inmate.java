package sample;

import java.time.LocalDate;

public class Inmate extends Person {


    // == Fields ==
    //minimumSecurity, maximumSecurity, Infirmary
    private String cellBlock;
    private int cellNumber;

    //Bunk A, Bunk B
    private String bunkAssignment;
    private String weight;
    private String height;
    private String race;

    //should be automatic at the time of booking
    private LocalDate bookingDate;

    //can only be edited into the system upon inmate release
    private LocalDate releaseDate;

    public Inmate( String firstName, String lastName, String weight,
                  String height, String race,String cellBlock, int cellNumber, String bunkAssignment) {
        super(firstName, lastName);
        this.cellBlock = cellBlock;
        this.cellNumber = cellNumber;
        this.bunkAssignment = bunkAssignment;
        this.weight = weight;
        this.height = height;
        this.race = race;
        this.bookingDate = LocalDate.now();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    public String getCellBlock() {
        return cellBlock;
    }

    public void setCellBlock(String cellBlock) {
        this.cellBlock = cellBlock;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getBunkAssignment() {
        return bunkAssignment;
    }

    public void setBunkAssignment(String bunkAssignment) {
        this.bunkAssignment = bunkAssignment;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getRace() {
        return this.race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Name: " + getFirstName() + " " + getLastName() + "\n" +
                "Weight: " + weight + "\n" +
                "Height: " + height + "\n" +
                "Race: " + race + "\n" +
                "Booking Date: " + bookingDate + "\n" +
                "Release Date: " + releaseDate;
    }
}
