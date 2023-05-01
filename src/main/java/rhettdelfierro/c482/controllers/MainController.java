package rhettdelfierro.c482.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MainController Initial screen of the app.
 */
public class MainController implements Initializable {
    Stage stage;
    Parent scene;


    @FXML
    private TextField searchPartTxt;

    @FXML
    private TextField searchProductTxt;

    @FXML
    private void changeScene(ActionEvent event, String viewName) throws IOException {
        int height = 1000;
        int width = 530;
        if (viewName == "main") {
            height = 530;
            width = 1000;
        }
        else if (viewName == "modify-part" || viewName == "add-part") {
            height = 530;
            width = 500;
        } else if (viewName == "modify-product" || viewName == "add-product") {
            height = 600;
            width = 1000;
        }

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/rhettdelfierro/c482/" + viewName + ".fxml"));
        stage.setScene(new Scene(scene, width, height));
        stage.show();
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        changeScene(event, "modify-part");
    }

    @FXML
    void OnActionModifyProduct(ActionEvent event) throws IOException {
        changeScene(event, "modify-product");
    }

    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        changeScene(event, "add-part");
    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        changeScene(event, "add-product");
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

    }

    @FXML
    void onActionExitProgram(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}