package sample;

import java.util.ArrayList;
import java.util.List;

public class MinimumSecurityBuilding extends Building{

    /*might need to populate prison cells upon creation and set cell nums from this class*/

    private static MinimumSecurityBuilding  minimumSecurityBuilding = null;
    private List<PrisonCell> cellBlock;

    private MinimumSecurityBuilding(){
        cellBlock = new ArrayList<>();
        createPrisonCells();

        //populate cellblocks with for loop
    }

    public List<PrisonCell> getCellBlock() {
        return cellBlock;
    }

    //add to PrisonCell
    //add to first cell and then first bed available
    @Override
    public boolean addInmate(Inmate inmate) {

        if((cellBlock != null) && (cellBlock.size() <= RoomLimit.MINIMUM_SECURITY_SIZE.getNumberOfRooms())){
            for(PrisonCell prisonCell: cellBlock){
                if(!inmate.equals(prisonCell.getBunkA())){
                    return prisonCell.addToBunkA(inmate);
                }else if(!inmate.equals(prisonCell.getBunkB())){
                    return prisonCell.addToBunkB(inmate);
                }
            }
        }
        return false;
    }

    //populates the cellblock with prison cells
    @Override
    public void createPrisonCells() {

        for(int i=0; i < RoomLimit.MINIMUM_SECURITY_SIZE.getNumberOfRooms(); i++){
            cellBlock.add(new PrisonCell());
        }
    }

    @Override
    public void assignToPrisonCell(Inmate inmate) {

    }

    @Override
    public List<Integer> getAvailableRoomsList() {
        if(minimumSecurityBuilding != null){
            List<Integer> cellNumbers = new ArrayList<>();
            for(PrisonCell cell: cellBlock){
                if(cell.hasAvailableBunks()){
                    cellNumbers.add(cell.getCellNumber());
                }
            return cellNumbers;
            }
        }
        return null;
    }

    //add first to new cell then remove from old cell location
    @Override
    public boolean moveInmate(Inmate inmate, int cellMoveNumber) {
        boolean isAddedToCell = false;
        for(PrisonCell prisonCell: cellBlock){
            if(cellMoveNumber == prisonCell.getCellNumber()){
                isAddedToCell = prisonCell.addToCell(inmate);
                if(isAddedToCell) {
                    return removeInmate(inmate);
                }else{
                    break;
                }
            }
        }
        return false;
    }

    @Override
    public PrisonCell findInmate(Inmate inmate) {

        for(PrisonCell prisonCell: cellBlock){
            if(prisonCell.getBunkA().equals(inmate)){
                return prisonCell;
            }else if(prisonCell.getBunkB().equals(inmate)){
                return prisonCell;
            }
        }
        return null;
    }

    @Override
    public boolean removeInmate(Inmate inmate) {
        for (PrisonCell prisonCell : cellBlock) {
            if (prisonCell.getBunkA().equals(inmate) || prisonCell.getBunkB().equals(inmate)) {
                return prisonCell.removeFromCell(inmate);
            }
        }
        return false;
    }

    public static MinimumSecurityBuilding getInstance(){

        if(minimumSecurityBuilding == null){
            minimumSecurityBuilding = new MinimumSecurityBuilding();
        }

        return minimumSecurityBuilding;
    }
}
