package rhettdelfierro.c482.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

    Helpers helpers = new Helpers();


    /**
     * Helper method to change scenes with selected part loaded.
     * @param event Action event
     * @throws IOException
     */
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        helpers.changeScene(event, "modify-part");
    }

    /**
     * Helper method to change scenes with selected product loaded.
     * @param event Action event
     * @throws IOException
     */
    @FXML
    void OnActionModifyProduct(ActionEvent event) throws IOException {
        helpers.changeScene(event, "modify-product");
    }

    /**
     * Helper method to change scenes
     * @param event Action event
     * @throws IOException
     */
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        helpers.changeScene(event, "add-part");
    }

    /**
     * Helper method to change scenes
     * @param event Action event
     * @throws IOException
     */
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        helpers.changeScene(event, "add-product");
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