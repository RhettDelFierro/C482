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

    @FXML
    void onActionOutsourcedRBtn(ActionEvent event) {
        System.out.println("Outsourced Radio Button Clicked");
    }

    @FXML
    void onActionPartInHouseRBtn(ActionEvent event) {
        System.out.println("In-House Radio Button Clicked");
    }
    @FXML
    void onActionCancelAddPart(ActionEvent event) {
        System.out.println("Cancel Button Clicked");
    }

    @FXML
    void onActionSavePart(ActionEvent event) {
        System.out.println("Save Button Clicked");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
