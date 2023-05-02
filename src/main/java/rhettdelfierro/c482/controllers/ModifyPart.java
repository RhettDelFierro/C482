package rhettdelfierro.c482.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPart implements Initializable {

    @FXML
    private ToggleGroup inHouseOrOutsourced;

    @FXML
    private TextField invNameTxt;

    @FXML
    private Label machineIDLabel;

    @FXML
    private TextField machineIdTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private TextField partIdTxt;

    @FXML
    private RadioButton partInHouseRBtn;

    @FXML
    private TextField partNameTxt;

    @FXML
    private RadioButton partOutsourcedRBtn;

    /**
     * Action event handler for choosing the Outsource Radio Button
     *
     * @param event the action event
     */
    @FXML
    void onActionOutsourcedRBtn(ActionEvent event) {
        machineIDLabel.setText("Company Name");
    }

    /**
     * Action event handler for choosing the In-House Radio Button
     *
     * @param event the action event
     */
    @FXML
    void onActionPartInHouseRBtn(ActionEvent event) {
        machineIDLabel.setText("Machine ID");
    }

    /**
     * Action event handler for clicking the Save Button. This will update and save the part to the inventory
     * data store and reroute the user to the main screen.
     * @param event the action event
     */
    @FXML
    void onActionUpdatePart(ActionEvent event) throws IOException {
        Helpers.changeScene(event, "main");
    }

    /**
     * Action event handler for clicking the Cancel Button. This will return to the main screen.
     *
     * @param event the action event
     */
    @FXML
    void onActionCancelUpdatePart(ActionEvent event) throws IOException {
        Helpers.changeScene(event, "main");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
