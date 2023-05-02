package rhettdelfierro.c482.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import rhettdelfierro.c482.models.Inventory;
import rhettdelfierro.c482.models.Part;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProduct implements Initializable {
    @FXML
    private TextField InvLvlTxt;

    @FXML
    private TableColumn<Part, Integer> allPartsInvLvlCol;

    @FXML
    private TableColumn<Part, Integer> allPartsPartIdCol;

    @FXML
    private TableColumn<Part, String> allPartsPartNameCol;

    @FXML
    private TableColumn<Part, Double> allPartsPriceCol;

    @FXML
    private TableView<Part> allPartsTbl;

    @FXML
    private TableColumn<Part, Integer> associatedPartsInvLvlCol;

    @FXML
    private TableColumn<Part, Integer> associatedPartsPartIdCol;

    @FXML
    private TableColumn<Part, String> associatedPartsPartNameCol;

    @FXML
    private TableColumn<Part, Double> associatedPartsPriceCol;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private Button onActionAddPart;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField productIDTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField allPartsTxt;

    @FXML
    void onActionCancel(ActionEvent event) {

    }

    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) {

    }
    @FXML
    void onActionFilterPart(ActionEvent event) {
        String searchText = allPartsTxt.getText();
        if (searchText.isEmpty()) {
            allPartsTbl.setItems(Inventory.getAllParts());
        } else {
            ObservableList<Part> results = Helpers.searchParts(searchText);
            if (results.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Program error.");
                alert.setContentText("No matching parts found for your search term: " + searchText + ".");
                alert.showAndWait();
            } else {
                allPartsTbl.setItems(results);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allPartsTbl.setItems(Inventory.getAllParts());

        allPartsPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartsPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartsInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
