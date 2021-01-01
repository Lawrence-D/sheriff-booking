package sample;

import java.time.LocalDate;

public class Inmate extends Person {


//    //minimumSecurity, maximumSecurity, Infirmary
//    private String cellBlock;
//
//    private String cellNumber;
//
//    //Bunk A, Bunk B
//    private String bunk;
    // == Fields ==
    private String weight;
    private String height;
    private String race;

    //should be automatic at the time of booking
    private LocalDate bookingDate;
    private LocalDate courtDate;

    //can only be edited into the system upon inmate release
    private LocalDate releaseDate;

    public Inmate(String firstName, String lastName, String weight,
                  String height, String race, LocalDate bookingDate,
                  LocalDate courtDate, LocalDate releaseDate) {
        super(firstName, lastName);
        this.weight = weight;
        this.height = height;
        this.race = race;
        this.bookingDate = bookingDate;
        this.courtDate = courtDate;
        this.releaseDate = releaseDate;
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

    public LocalDate getCourtDate() {
        return courtDate;
    }

    public void setCourtDate(LocalDate courtDate) {
        this.courtDate = courtDate;
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
                "Court Date: " + courtDate + "\n" +
                "Release Date: " + releaseDate;
    }
}
