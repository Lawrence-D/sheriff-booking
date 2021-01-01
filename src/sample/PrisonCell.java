package sample;

public class PrisonCell {

    private int cellNumVal = 1;
    private int cellNumber;
    private Inmate bunkA;
    private Inmate bunkB ;

    public PrisonCell() {
        this.cellNumber = this.cellNumVal;
        this.cellNumVal++;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public boolean addToCell(Inmate inmate){

        boolean isSuccess = addToBunkA(inmate);

        if(isSuccess){
            return true;
        }else{
            return addToBunkB(inmate);
        }
    }

    public boolean removeFromCell(Inmate inmate){

        if(bunkA.equals(inmate)){
            return addToBunkA(null);
        }else if(bunkB.equals(inmate)){
            return addToBunkB(null);
        }
        return false;
    }

    public boolean hasAvailableBunks(){
        return getBunkA() != null || getBunkB() != null;
    }


    public Inmate getBunkA() {
        return bunkA;
    }

    public boolean addToBunkA(Inmate bunkA) {

        if(this.bunkA == null)
        {
            this.bunkA = bunkA;
            return true;
        }else{
            System.out.println("Bunk A is already taken.");
            return false;
        }
    }

    public Inmate getBunkB() {
        return bunkB;
    }

    public boolean addToBunkB(Inmate bunkB) {

        if(this.bunkB == null)
        {
            this.bunkB = bunkA;
            return true;
        }else{
            System.out.println("Bunk B is already taken.");
            return false;
        }
    }
}
