package rhettdelfierro.c482.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FUTURE ENHANCEMENT: Rather than have multiple views and controllers for add/modify part,
 * perhaps can have one view and the controller can dynamically update the fields.
 */
public class AddPart implements Initializable {

    @FXML
    private TextField invNameTxt;

    @FXML
    private Label machineIDLabel;

    @FXML
    private TextField machineIdTxt;

    @FXML
    private TextField maxPriceTxt;

    @FXML
    private TextField minPriceTxt;

    @FXML
    private VBox partIdTxt;

    @FXML
    private RadioButton partInHouseRBtn;

    @FXML
    private TextField partNameTxt;

    @FXML
    private RadioButton partOutsourcedRBtn;

    /**
     * Action event handler for choosing the Outsource Radio Button
     * @param event the action event
     */
    @FXML
    void onActionOutsourcedRBtn(ActionEvent event) {
        System.out.println("Outsourced Radio Button Clicked");
    }

    /**
     * Action event handler for choosing the In-House Radio Button
     * @param event the action event
     */
    @FXML
    void onActionPartInHouseRBtn(ActionEvent event) {
        System.out.println("In-House Radio Button Clicked");
    }

    /**
     * Action event handler for clicking the Cancel Button. This will return to the main screen.
     * @param event the action event
     */
    @FXML
    void onActionCancelAddPart(ActionEvent event) {
        System.out.println("Cancel Button Clicked");
    }

    /**
     * Action event handler for clicking the Save Button. This will save the part to the inventory data store and
     * reroute the user to the main screen.
     * @param event the action event
     */
    @FXML
    void onActionSavePart(ActionEvent event) {
        System.out.println("Save Button Clicked");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
