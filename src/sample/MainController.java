package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    //Windows
    @FXML
    private GridPane bookingPane;

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
    public void onButtonClicked(ActionEvent e) throws Exception {
        Stage stage  = new Stage();

        if(e.getSource().equals(bookInmate))
        {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/booking.fxml"));
            stage.setTitle("Sheriff Office Booking System");
            stage.setScene(new Scene(root, 450, 425));
        }

        stage.show();
    }


    @FXML
    public void bookingScreen() throws IOException {
        Stage stage  = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("fxml/booking.fxml"));
        stage.setTitle("Sheriff Office Booking System");
        stage.setScene(new Scene(root, 465, 425));

        stage.show();
    }

    @FXML
    public void exitProgram() {
        System.exit(0);
    }


}
