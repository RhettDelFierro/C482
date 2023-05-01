package rhettdelfierro.c482.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProduct implements Initializable {
    @FXML
    private TextField InvLvlTxt;

    @FXML
    private TableColumn<?, ?> allPartsInvLvlCol;

    @FXML
    private TableColumn<?, ?> allPartsPartIdCol;

    @FXML
    private TableColumn<?, ?> allPartsPartNameCol;

    @FXML
    private TableColumn<?, ?> allPartsPriceCol;

    @FXML
    private TableView<?> allPartsTbl;

    @FXML
    private TableColumn<?, ?> associatedPartsInvLvlCol;

    @FXML
    private TableColumn<?, ?> associatedPartsPartIdCol;

    @FXML
    private TableColumn<?, ?> associatedPartsPartNameCol;

    @FXML
    private TableColumn<?, ?> associatedPartsPriceCol;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField mixTxt;

    @FXML
    private Button onActionAddPart;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField productIDTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    void onActionCancel(ActionEvent event) {

    }

    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
