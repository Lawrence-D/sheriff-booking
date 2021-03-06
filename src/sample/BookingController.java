package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingController {


    @FXML
    MinimumSecurityBuilding minimumSecurityBuilding;

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

    //boolean for confirming inmate details are accurate
    private boolean isConfirmed = false;


    public void initialize()
    {
        minimumSecurityBuilding = MinimumSecurityBuilding.getInstance();

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

                prisonCellListView.getItems().setAll(createCellList(minimumSecurityBuilding.getCellBlock()));
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

                //assigns inmate to a call and bunk
                for(PrisonCell prisonCell : minimumSecurityBuilding.getCellBlock())
                {

                    if(prisonCellListView.getValue().equals(Integer.toString(prisonCell.getCellNumber())))
                    {

                        if(bunkListView.getValue().equals("A")) //Bunk A
                        {
                            Inmate newInmate = new Inmate(firstName.getText(),lastName.getText(), Integer.toString(weight.getValue()), height.getValue(),
                                    getRaceEthnicityString(), cellBlockListView.getValue(), Integer.parseInt(prisonCellListView.getValue()), bunkListView.getValue());

                            showConfirmationDialog(newInmate);
                            break;

                        }else if(bunkListView.getValue().equals("B")) //Bunk B
                        {
                            Inmate newInmate = new Inmate(firstName.getText(),lastName.getText(), Integer.toString(weight.getValue()), height.getValue(),
                                    getRaceEthnicityString(), cellBlockListView.getValue(), Integer.parseInt(prisonCellListView.getValue()), bunkListView.getValue());

                            showConfirmationDialog(newInmate);
                            break;
                        }
                    }
                }

                if(isConfirmed){
                    Stage stage = (Stage) submitButton.getScene().getWindow();
                    stage.close();
                }

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

    public void showConfirmationDialog(Inmate inmate)
    {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(inmate.toString());

        confirmationAlert.setContentText("Are the details above correct?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if(result.isPresent() && (result.get() == ButtonType.OK)) {

            //add check if inmate exist already functionality
            boolean inmateAdded = minimumSecurityBuilding.addInmate(inmate);

            if(inmateAdded){
                isConfirmed = true;

                confirmationAlert.close();
                //add success dialog later
            }

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

        if(cellBlockListView.getValue() != null && (prisonCellListView.getValue() == null || prisonCellListView.getValue().equals("N/A"))){
            num++;
            errors += num + ". PrisonCell number needs to be selected.\n";
        }

        if(prisonCellListView.getValue() != null && (bunkListView.getValue() == null || bunkListView.getValue().equals("N/A")))
        {
            num++;
            errors += num + ". Bunk needs to be selected.\n";
        }

        if(getRaceEthnicityString().isEmpty())
        {
            num++;
            errors += num + ". Race and ethnicity needs to be selected.\n";
        }

        return errors;
    }

    //Combines selections from various checkBoxes
    public List<String> getRaceEthnicityString()
    {
        List<String> raceEthnicity = new ArrayList<>();

        if(americanNative.isSelected())
            raceEthnicity.add(americanNative.getText());
        if(asian.isSelected())
            raceEthnicity.add(asian.getText());
        if(black.isSelected())
            raceEthnicity.add(black.getText());
        if(latino.isSelected())
            raceEthnicity.add(latino.getText());
        if(pacificIslander.isSelected())
            raceEthnicity.add(pacificIslander.getText());
        if(white.isSelected())
            raceEthnicity.add(white.getText());

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

    //returns a list of available, bunks Bunk A and/or Bunk B
    public List<String> createBunkList(String cellNum, String cellBlockString)
    {
        List<String> bunkList = new ArrayList<>();

        if(cellBlockString.equals("Minimum Security"))
        {
            for(PrisonCell prisonCell : minimumSecurityBuilding.getCellBlock())
            {
                if(("" + prisonCell.getCellNumber()).equals(cellNum))
                {
                    if(prisonCell.getBunkA() == null) {
                        bunkList.add("A");
                    }

                    if(prisonCell.getBunkB() == null) {
                        bunkList.add("B");
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
