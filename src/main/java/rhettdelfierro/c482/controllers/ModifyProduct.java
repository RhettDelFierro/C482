package rhettdelfierro.c482.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import rhettdelfierro.c482.models.Inventory;
import rhettdelfierro.c482.models.Part;
import rhettdelfierro.c482.models.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for Modify Product page.
 * RUNTIME ERROR: parseInt() and parseDouble() would throw an IOException here, but we guard against that by using
 *                the checkValidInt and checkValidDouble helper functions at the top of the methods.
 */
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
    private TableView<Part> associatedPartsTbl;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField productIDTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField allPartsTxt;

    @FXML
    private Product currentProduct;

    /**
     * Adds the part to product.
     * @param event action event
     */
    @FXML
    void onActionAddPart(ActionEvent event) {
        Part selectedPart = allPartsTbl.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Helpers.showErrorDialog("Please select a part to add.");
            return;
        }
        currentProduct.addAssociatedPart(selectedPart);
        associatedPartsTbl.setItems(currentProduct.getAllAssociatedParts());
    }

    /**
     * Switches back to the main screen from the modify product page.
     * @param event action event.
     * @throws IOException if the main screen is not found.
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Helpers.changeScene(event, "main");
    }

    /**
     * Removes associated part fom product.
     * @param event action event
     */
    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {
        Part selectedPart = associatedPartsTbl.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Helpers.showErrorDialog("Please select a part to remove.");
            return;
        }
        currentProduct.deleteAssociatedPart(selectedPart);
        associatedPartsTbl.setItems(currentProduct.getAllAssociatedParts());
    }

    /**
     * Saves the product to the inventory.
     * @param event action event
     * @throws IOException if the main screen is not found when routing.
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        if (!Helpers.checkValidInt(InvLvlTxt.getText())) {
            Helpers.showErrorDialog("Inventory must be a valid integer.");
            return;
        }
        if (!(Helpers.checkValidInt(minTxt.getText()) && Helpers.checkValidInt(maxTxt.getText()))) {
            Helpers.showErrorDialog("Min and Max must be a valid integer.");
            return;
        }
        if (Integer.parseInt(minTxt.getText()) > Integer.parseInt(maxTxt.getText())) {
            Helpers.showErrorDialog("Min must be less than Max.");
            return;
        }
        if (Integer.parseInt(minTxt.getText()) < 0 || Integer.parseInt(maxTxt.getText()) < 0) {
            Helpers.showErrorDialog("Min and Max must be greater than 0.");
            return;
        }
        if ((Integer.parseInt(InvLvlTxt.getText()) < Integer.parseInt(minTxt.getText())) ||
                (Integer.parseInt(InvLvlTxt.getText())) > Integer.parseInt(maxTxt.getText())
        ) {
            Helpers.showErrorDialog("Inventory must be between Min and Max");
            return;
        }
        if (!Helpers.checkValidDouble(priceTxt.getText())) {
            Helpers.showErrorDialog("Price must be a valid amount.");
            return;
        }

        String name = productNameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(InvLvlTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        Product newProduct = new Product(currentProduct.getId(), name, price, stock, min, max);
        int index = Helpers.findIndexForProduct(currentProduct.getId());
        Inventory.updateProduct(index, newProduct);
        Helpers.changeScene(event, "main");
    }

    /**
     * Filters/searches for the part in all parts table.
     *
     * @param event action event
     */
    @FXML
    void onActionFilterPart(ActionEvent event) {
        String searchText = allPartsTxt.getText();
        if (searchText.isEmpty()) {
            allPartsTbl.setItems(Inventory.getAllParts());
        } else {
            ObservableList<Part> results = Helpers.searchParts(searchText);
            if (results.isEmpty()) {
                Helpers.showErrorDialog("No matching parts found for your search term: " + searchText + ".");
            } else {
                allPartsTbl.setItems(results);
            }
        }
    }

    /**
     * This method is used to load the product data as chosen from the main screen's table view.
     * @param product The product to be loaded into the modify product screen.
     */
    void sendProduct(Product product) {
        productIDTxt.setText(String.valueOf(product.getId()));
        productNameTxt.setText(product.getName());
        InvLvlTxt.setText(String.valueOf(product.getStock()));
        priceTxt.setText(String.valueOf(product.getPrice()));
        minTxt.setText(String.valueOf(product.getMin()));
        maxTxt.setText(String.valueOf(product.getMax()));
        currentProduct = product;
        associatedPartsTbl.setItems(product.getAllAssociatedParts());
        associatedPartsPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartsPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartsInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Initializes the modify product screen.
     * This method is called automatically when the modify product screen is loaded.
     * We're mainly using this to populate the all parts table.
     *
     * @param url url
     * @param resourceBundle resource bundle folder
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allPartsTbl.setItems(Inventory.getAllParts());

        allPartsPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartsPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartsInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
