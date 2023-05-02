package rhettdelfierro.c482.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private TableView<Part> associatedPartsTbl;

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
    private Product defaultProduct = new Product(0, "", 0.0, 0, 0, 0);

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Helpers.changeScene(event, "main");
    }

    @FXML
    void onActionAddPart(ActionEvent event) {
        Part selectedPart = allPartsTbl.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Helpers.showErrorDialog("Please select a part to add.");
            return;
        }
        defaultProduct.addAssociatedPart(selectedPart);
        associatedPartsTbl.setItems(defaultProduct.getAllAssociatedParts());
    }

    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {
        Part selectedPart = associatedPartsTbl.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Helpers.showErrorDialog("Please select a part to remove.");
            return;
        }
        defaultProduct.deleteAssociatedPart(selectedPart);
        associatedPartsTbl.setItems(defaultProduct.getAllAssociatedParts());
    }

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
        if (Integer.parseInt(minTxt.getText()) < 0 || Integer.parseInt(maxTxt.getText()) < 0) {
            Helpers.showErrorDialog("Min and Max must be greater than 0.");
            return;
        }
        if (Integer.parseInt(minTxt.getText()) > Integer.parseInt(maxTxt.getText())) {
            Helpers.showErrorDialog("Min must be less than Max.");
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

        defaultProduct.setName(productNameTxt.getText());
        defaultProduct.setStock(Integer.parseInt(InvLvlTxt.getText()));
        defaultProduct.setPrice(Double.parseDouble(priceTxt.getText()));
        defaultProduct.setMin(Integer.parseInt(minTxt.getText()));
        defaultProduct.setMax(Integer.parseInt(maxTxt.getText()));
        Inventory.addProduct(defaultProduct);
        Helpers.changeScene(event, "main");
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allPartsTbl.setItems(Inventory.getAllParts());

        allPartsPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartsPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartsInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        int id = Helpers.getAutoGeneratedProductId();
        Product newProduct = new Product(id, "", 0.0, 1, 1, 5);
        defaultProduct = newProduct;

        associatedPartsPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartsPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartsInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
