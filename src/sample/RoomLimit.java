package sample;

public enum RoomLimit {

    HOSPITAL_SIZE(25),
    MINIMUM_SECURITY_SIZE(50),
    MAXIMUM_SECURITY_SIZE(25);

    private final int numberOfRooms;

    RoomLimit(int numberOfRooms){
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfRooms(){
        return numberOfRooms;
    }
}
