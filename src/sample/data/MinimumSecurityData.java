package sample.data;

import sample.MinimumSecurityBuilding;
import sample.PrisonCell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class MinimumSecurityData {

    private static MinimumSecurityData instance = new MinimumSecurityData();
    private static String fileName = "minSecurity.txt";
    private MinimumSecurityBuilding minimumSecurityBuilding;
    private List<PrisonCell> minimumSecurityCellblock;
    private DateTimeFormatter formatter;

    private MinimumSecurityData()
    {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        minimumSecurityBuilding = MinimumSecurityBuilding.getInstance();
        minimumSecurityCellblock = minimumSecurityBuilding.getCellBlock();
    }

    public static MinimumSecurityData getInstance()
    {
        return instance;
    }

    //temporary until we change this to ObservableList
    //having issue here
    public List<PrisonCell> getMinimumSecurityCellblock() {
        return minimumSecurityCellblock;
    }

    //fix need to keep track of prisoncell num
    //does the minimumsecurity need to populate and then write over itself?
    public void loadMinimumSecurityList() throws IOException {

        Path path = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try{

            while((input = br.readLine()) != null){

                String[] inmate = input.split("\t");

                String firstName = inmate[0];
                String lastName= inmate[1];
                String weight= inmate[2];
                String height = inmate[3];
                String race = inmate[4];
                LocalDate bookingDate = LocalDate.parse(inmate[5], formatter);
                LocalDate courtDate = LocalDate.parse(inmate[6], formatter);
                LocalDate releaseDate = LocalDate.parse(inmate[7], formatter);

                //inmate should keep track of bed and cell
                //minsecurityBuilding should add inmate to correct cell dynamically

            }

        }finally{
            try{
                if(br != null){
                    br.close();
                }
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedReader " + br);
            }
        }


    }

    //fix need to keep track of prisoncell num
    public void saveMinimumSecurityList() throws IOException{

        Path path = Paths.get(fileName);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try{
            Iterator<PrisonCell> iter = minimumSecurityBuilding.getCellBlock().iterator();
            while(iter.hasNext()){
                PrisonCell prisonCell = iter.next();
                bw.write(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
                        prisonCell.getBunkA().getFirstName(),
                        prisonCell.getBunkA().getLastName(),
                        prisonCell.getBunkA().getWeight(),
                        prisonCell.getBunkA().getHeight(),
                        prisonCell.getBunkA().getRace(),
                        prisonCell.getBunkA().getBookingDate(),
                        prisonCell.getBunkA().getCourtDate(),
                        prisonCell.getBunkA().getReleaseDate()
                        ));
                bw.newLine();

                bw.write(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
                        prisonCell.getBunkB().getFirstName(),
                        prisonCell.getBunkB().getLastName(),
                        prisonCell.getBunkB().getWeight(),
                        prisonCell.getBunkB().getHeight(),
                        prisonCell.getBunkB().getRace(),
                        prisonCell.getBunkB().getBookingDate(),
                        prisonCell.getBunkB().getCourtDate(),
                        prisonCell.getBunkB().getReleaseDate()
                ));
                bw.newLine();
            }
        }finally {
            try{
                if(bw != null){
                    bw.close();
                }
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter " + bw);
            }
        }
    }

}
