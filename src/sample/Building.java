package sample;

import java.util.List;

public abstract class Building {

    public abstract boolean addInmate(Inmate inmate);
    public abstract void createPrisonCells();
    public abstract void assignToPrisonCell(Inmate inmate);
    public abstract List<Integer> getAvailableRoomsList();
    public abstract boolean moveInmate(Inmate inmate, int cellMoveNumber);
    public abstract PrisonCell findInmate(Inmate inmate);
    public abstract boolean removeInmate(Inmate inmate);

}
