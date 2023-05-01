package rhettdelfierro.c482.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rhettdelfierro.c482.models.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProduct implements Initializable {
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
    @FXML
    void goToMainScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/rhettdelfierro/c482/main.fxml"));
        stage.setScene(new Scene(scene, 1000, 530));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
