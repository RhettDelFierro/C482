package rhettdelfierro.c482.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

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
    void onActionCancelUpdatePart(ActionEvent event) {

    }

    @FXML
    void onActionOutsourcedRBtn(ActionEvent event) {

    }

    @FXML
    void onActionPartInHouseRBtn(ActionEvent event) {

    }

    @FXML
    void onActionUpdatePart(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
