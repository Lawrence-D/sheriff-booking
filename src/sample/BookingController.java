package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingController {


    @FXML
    List<PrisonCell> minSecurity;

    //ListViews for ComboBox's
    @FXML
    private ComboBox<String> cellBlockListView;
    @FXML
    private ComboBox<String> prisonCellListView;
    @FXML
    private ComboBox<String> bunkListView;

    //Form field variables
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private Spinner<Integer> weight;
    @FXML
    private ComboBox<String> height;

    //Race and ethnicity checkBox form values
    @FXML
    private CheckBox americanNative;
    @FXML
    private CheckBox asian;
    @FXML
    private CheckBox black;
    @FXML
    private CheckBox latino;
    @FXML
    private CheckBox pacificIslander;
    @FXML
    private CheckBox white;

    //id for GridPane in booking.fxml
    @FXML
    private GridPane bookingGridPane;

    //PrisonCell Block Strings
    private String minSecurityString = "Minimum Security";

    @FXML
    private Button submitButton;

    //label for error dialog window
    @FXML
    Label errorText;


    public void initialize()
    {
        minSecurity = new ArrayList<>();

        for(int i=0; i < 10; i++)
        {
            minSecurity.add(new PrisonCell());
        }

        //initialize cell number and bunks to not available
        //prisonCellListView.getItems().add("N/A");
        //bunkListView.getItems().add("N/A");

    }

    @FXML
    public void onComboBoxClicked(ActionEvent e)
    {
        //click on cell block combo box
        //populates the cell combo box
        if(e.getSource().equals(cellBlockListView))
        {
            if(cellBlockListView.getValue().equals(minSecurityString))
            {
                System.out.println(minSecurityString);
                //populates cell combo box listview
                prisonCellListView.getItems().setAll(createCellList(minSecurity));
            }
        }


        if(e.getSource().equals(prisonCellListView))
        {
            String selectedCell = prisonCellListView.getValue();
            //System.out.println(selectedCell);

            if(cellBlockListView.getValue() != null)
                bunkListView.getItems().setAll(createBunkList(selectedCell, cellBlockListView.getValue()));
        }
    }

    @FXML
    public void onSubmitButtonClicked(ActionEvent e)
    {
        if(e.getSource().equals(submitButton))
        {
            if(!getErrorString().isBlank() || !getErrorString().isEmpty()){
                //failure to submit
                showErrorDialog(getErrorString());
            }else{

                //currently minsecurity could be expanded
                //assigns inmate to a call and bunk
                for(PrisonCell prisonCell : minSecurity)
                {
                    System.out.println("cellNum = " + prisonCell.getCellNumber());
                    System.out.println("prisonCellListView = " + prisonCellListView.getValue());
                    if(prisonCellListView.getValue().equals(Integer.toString(prisonCell.getCellNumber()))) //error here
                    {
                        System.out.println("CellNum's match");
                        if(bunkListView.getValue().equals("Bunk A"))
                        {
                            //fix bunk value
                            Inmate newInmate = new Inmate(firstName.getText(),lastName.getText(), Integer.toString(weight.getValue()), height.getValue(),
                                    getRaceEthnicityString(), cellBlockListView.getValue(), Integer.parseInt(prisonCellListView.getValue()), bunkListView.getValue());

                            showConfirmationDialog(newInmate);
                            break;
                        }else if(bunkListView.getValue() == "Bunk B")
                        {
                            Inmate newInmate = new Inmate(firstName.getText(),lastName.getText(), Integer.toString(weight.getValue()), height.getValue(),
                                    getRaceEthnicityString(), cellBlockListView.getValue(), Integer.parseInt(prisonCellListView.getValue()), bunkListView.getValue());

                            showConfirmationDialog(newInmate);
                            break;
                        }
                    }
                }
                
                //confirmation dialogue window
            }
        }
    }

    public void showErrorDialog(String errorString)
    {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Missing Fields/Incorrect Information Error");
        errorAlert.setHeaderText("Missing Fields/Incorrect Information: \n" + errorString);

        errorAlert.setContentText("These fields must be filled to add an inmate to the system.");
        Optional<ButtonType> result = errorAlert.showAndWait();

        if(result.isPresent() && (result.get() == ButtonType.OK))
        {
            errorAlert.close();
        }
    }

    //not reaching this method
    public void showConfirmationDialog(Inmate inmate)
    {
        System.out.println("Inside Show Confirmation Dialog");
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(inmate.toString());

        confirmationAlert.setContentText("Are the details above correct?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if(result.isPresent() && (result.get() == ButtonType.OK)) {
            //boolean inmateAdded = cell.setBunkA(inmate);
        }else if(result.isPresent() && (result.get() == ButtonType.CANCEL)) {
            confirmationAlert.close();
        }

    }

    //Combines any errors into a String
    public String getErrorString()
    {
        int num = 0;
        String errors = "";

        if(firstName.getText().isEmpty() || firstName.getText().isBlank()) {
            num++;
            errors +=  num + ". First name is missing.\n";
        }

        if(lastName.getText().isEmpty() || lastName.getText().isBlank()){
            num++;
            errors += num + ". Last name is missing.\n";
        }

        if(height.getValue() == null){
            num++;
            errors += num + ". Height is missing.\n";
        }


        if(cellBlockListView.getValue() == null) {
            errors += ((++num) + ". PrisonCell block needs to be selected.\n" +
                    (++num) + ". PrisonCell number needs to be selected.\n" +
                    (++num) + ". Bunk needs to be selected.\n");
        }

        if(cellBlockListView.getValue() != null && (prisonCellListView.getValue() == null || prisonCellListView.getValue() == "N/A")){
            num++;
            errors += num + ". PrisonCell number needs to be selected.\n";
        }

        if(prisonCellListView.getValue() != null && (bunkListView.getValue() == null || bunkListView.getValue() == "N/A"))
        {
            num++;
            errors += num + ". Bunk needs to be selected.\n";
        }

        if(getRaceEthnicityString().isEmpty() || getRaceEthnicityString().isBlank())
        {
            num++;
            errors += num + ". Race and ethnicity needs to be selected.\n";
        }

        return errors;
    }

    //Combines selections from various checkBoxes
    public String getRaceEthnicityString()
    {
        String raceEthnicity = "";

        if(americanNative.isSelected())
            raceEthnicity += (americanNative.getText() + "\t");
        if(asian.isSelected())
            raceEthnicity += (asian.getText() + "\t");
        if(black.isSelected())
            raceEthnicity += (black.getText() + "\t");
        if(latino.isSelected())
            raceEthnicity += (latino.getText() + "\t");
        if(pacificIslander.isSelected())
            raceEthnicity += (pacificIslander.getText() + "\t");
        if(white.isSelected())
            raceEthnicity += (white.getText() + "\t");

        return raceEthnicity;
    }

    //returns a list of prison cells for a combobox
    public List<String> createCellList(List<PrisonCell> prisonCellBlock)
    {
        List<String> cellList = new ArrayList<>();

        for (PrisonCell prisonCellNum : prisonCellBlock)
        {
            cellList.add(""+ prisonCellNum.getCellNumber());
        }
        return cellList;
    }

    //returns a list of bunks Bunk A and/or Bunk B
    public List<String> createBunkList(String cellNum, String cellBlockString)
    {
        List<String> bunkList = new ArrayList<>();

        if(cellBlockString.equals("Minimum Security"))
        {
            for(PrisonCell prisonCell : minSecurity)
            {
                if(("" + prisonCell.getCellNumber()).equals(cellNum))
                {
                    if(prisonCell.getBunkA() == null) {
                        bunkList.add("Bunk A");
                    }

                    if(prisonCell.getBunkB() == null) {
                        bunkList.add("Bunk B");
                    }

                    if(bunkList.isEmpty()){
                        bunkList.add("None Available");
                    }
                    break;
                }
            }
        }
        return bunkList;
    }
}
