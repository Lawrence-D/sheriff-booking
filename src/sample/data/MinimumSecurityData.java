package sample.data;

import sample.MinimumSecurityBuilding;
import sample.PrisonCell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class MinimumSecurityData {

    private static MinimumSecurityData instance = new MinimumSecurityData();

    private static String fileName = "minSecurity.txt";

    private MinimumSecurityBuilding minimumSecurityBuilding;
    private DateTimeFormatter formatter;

    private MinimumSecurityData()
    {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        minimumSecurityBuilding = MinimumSecurityBuilding.getInstance();

    }

    public static MinimumSecurityData getInstance()
    {
        return instance;
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
