package rhettdelfierro.c482.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
 *
 * RUNTIME ERROR: parseInt() would throw an IOException here, but we guard against that by using the checkValidInt
 *                helper functions.
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
     * Helper method to change scenes
     *
     * @param event Action event
     * @throws IOException and IOException that bubbles up.
     */
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        Helpers.changeScene(event, "add-part");
    }

    /**
     * Helper method to change scenes
     *
     * @param event Action event
     * @throws IOException an IOException that bubbles up.
     */
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        Helpers.changeScene(event, "add-product");
    }

    /**
     * Deletes parts.
     *
     * @param event Action event
     */
    @FXML
    void onActionDeletePart(ActionEvent event) {
        Part partForDeletion = partsTableView.getSelectionModel().getSelectedItem();
        if (partForDeletion == null) {
            Helpers.showErrorDialog("There is no part selected for deletion.");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm deletion.");
            alert.setContentText("Are you sure you want to delete the part: " + partForDeletion.getName() + "?");
            alert.showAndWait();
            if (alert.getResult() != ButtonType.OK) {
                return;
            }
            Inventory.deletePart(partsTableView.getSelectionModel().getSelectedItem());
        }

    }

    /**
     * Deletes product if there are no associated parts.
     * @param event Event object.
     */
    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        Product productForDeletion = productsTableView.getSelectionModel().getSelectedItem();
        if (productForDeletion == null) {
            Helpers.showErrorDialog("There is no product selected for deletion.");
        } else if (productForDeletion.getAllAssociatedParts().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm deletion.");
            alert.setContentText("Are you sure you want to delete the product: " + productForDeletion.getName() + "?");
            alert.showAndWait();
            if (alert.getResult() != ButtonType.OK) {
                return;
            }
            Inventory.deleteProduct(productsTableView.getSelectionModel().getSelectedItem());
        } else {
            Helpers.showErrorDialog("You cannot delete a product that has associated parts.");
        }
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
            ObservableList<Part> results = Helpers.searchParts(searchText);
            if (results.isEmpty()) {
                Helpers.showErrorDialog("No matching parts found for your search term: " + searchText + ".");
            } else {
                partsTableView.setItems(results);
            }
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
            ObservableList<Product> results = Helpers.searchProducts(searchText);
            if (results.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Program error.");
                alert.setContentText("No matching products found for your search term: " + searchText + ".");
                alert.showAndWait();
            } else {
                productsTableView.setItems(results);
            }
        }
    }

    /**
     * Exits program.
     *
     * @param event Action event
     */
    @FXML
    void onActionExitProgram(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Helper method to change scenes with selected Part loaded.
     *
     * @param event Action event
     * @throws IOException an IOEXception that will throw if the resource fails to fetch.
     */
    @FXML
    public void onActionModifyPart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Helpers.class.getResource("/rhettdelfierro/c482/modify-part.fxml"));
        loader.load();
        ModifyPart controller = loader.getController();
        controller.sendPart(partsTableView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene, 500, 530));
        stage.showAndWait();
    }

    /**
     * Helper method to change scenes with selected Product loaded.
     *
     * RUNTIME ERROR: at javafx.fxml/javafx.fxml.FXMLLoader.load(FXMLLoader.java:2516)
     * at rhettdelfierro.c482/rhettdelfierro.c482.controllers.MainController.onActionModifyProduct(MainController.java:218)
     *
     * I gave the wrong filepath for the resource and this caused a runtime error.
     * I resolved this error by correcting the resource path.
     *
     * @param event Action event
     * @throws IOException an IOEXCeption that throws if the resource fails to fetch.
     */
    @FXML
    public void onActionModifyProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Helpers.class.getResource("/rhettdelfierro/c482/modify-product.fxml"));
        loader.load();
        ModifyProduct controller = loader.getController();
        controller.sendProduct(productsTableView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene, 1000, 600));
        stage.showAndWait();
    }

    /**
     * Initializes the controller class.
     * Populates both tables with init data primarily.
     *
     * @param url URL
     * @param resourceBundle resource bundle folder.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTableView.setItems(Inventory.getAllParts());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTableView.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

//        partsTableView.getSelectionModel().select(Inventory.lookupPart(5));
    }
}