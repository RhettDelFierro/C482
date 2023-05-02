package rhettdelfierro.c482.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import rhettdelfierro.c482.models.Inventory;
import rhettdelfierro.c482.models.Part;
import rhettdelfierro.c482.models.Product;

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
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInventoryLvlCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInventoryLvlCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private TextField searchPartTxt;

    @FXML
    private TextField searchProductTxt;


    /**
     * Helper method to change scenes with selected part loaded.
     *
     * @param event Action event
     * @throws IOException
     */
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        Helpers.changeScene(event, "modify-part");
    }

    /**
     * Helper method to change scenes with selected product loaded.
     *
     * @param event Action event
     * @throws IOException
     */
    @FXML
    void OnActionModifyProduct(ActionEvent event) throws IOException {
        Helpers.changeScene(event, "modify-product");
    }

    /**
     * Helper method to change scenes
     *
     * @param event Action event
     * @throws IOException
     */
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        Helpers.changeScene(event, "add-part");
    }

    /**
     * Helper method to change scenes
     *
     * @param event Action event
     * @throws IOException
     */
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        Helpers.changeScene(event, "add-product");
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

    }

    /**
     * Will search for products by name or id and populate table.
     * @param event Event object.
     */
    @FXML
    void onActionFilterPart(ActionEvent event) {
        String searchText = searchPartTxt.getText();
        if (searchText.isEmpty()) {
            partsTableView.setItems(Inventory.getAllParts());
        } else {
            ObservableList<Part> parts = Helpers.searchParts(searchText);
            partsTableView.setItems(parts);
        }
    }

    /**
     * Will search for products by name or id and populate table.
     * @param event Event object.
     */
    @FXML
    void onActionFilterProduct(ActionEvent event) {
        String searchText = searchProductTxt.getText();
        if (searchText.isEmpty()) {
            productsTableView.setItems(Inventory.getAllProducts());
        } else {
            ObservableList<Product> products = Helpers.searchProducts(searchText);
            productsTableView.setItems(products);
        }
    }

    @FXML
    void onActionExitProgram(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTableView.setItems(Inventory.getAllParts());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}