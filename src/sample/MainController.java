package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainController {
    //Windows
    @FXML
    private GridPane bookingPane;

    //find action method to click on menu items
    @FXML
    private MenuItem menuBookInmate;

    //buttons
    @FXML
    private Button bookInmate;
    @FXML
    private Button moveInmate;
    @FXML
    private Button visitor;
    @FXML
    private Button credits;

    @FXML
    public void onButtonClicked(ActionEvent e) throws Exception
    {
        Stage stage  = new Stage();

        if(e.getSource().equals(bookInmate))
        {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/booking.fxml"));
            stage.setTitle("Sheriff Office Booking System");
            stage.setScene(new Scene(root, 600, 490));
        }

        stage.show();
    }
}
