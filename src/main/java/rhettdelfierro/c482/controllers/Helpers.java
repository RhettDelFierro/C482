package rhettdelfierro.c482.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import rhettdelfierro.c482.models.Inventory;
import rhettdelfierro.c482.models.Part;
import rhettdelfierro.c482.models.Product;

import java.io.IOException;
import java.util.Objects;

/** Helpers methods for the app. */
public class Helpers {
    /**
     * Helper method to change scenes
     *
     * @param event the action event
     * @param viewName the name of the view to change to (add-part, modify-part, add-product, modify-product)
     * @throws IOException if an error occurs during I/O operations
     */
    public static void changeScene(ActionEvent event, String viewName) throws IOException {
        int height = 530;
        int width = 1000;
        if (Objects.equals(viewName, "main")) {
            height = 530;
            width = 1000;
        }
        else if (Objects.equals(viewName, "modify-part") || Objects.equals(viewName, "add-part")) {
            height = 530;
            width = 500;
        } else if (Objects.equals(viewName, "modify-product") || Objects.equals(viewName, "add-product")) {
            height = 600;
            width = 1000;
        }

        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Helpers.class.getResource("/rhettdelfierro/c482/" + viewName + ".fxml"));
        stage.setScene(new Scene(scene, width, height));
        stage.show();
    }

    /**
     * Helper method to search for parts by name or id.
     *
     * @param searchText the text to search for
     * @return the list of parts
     */
    public static ObservableList<Part> searchParts(String searchText) {
        ObservableList<Part> parts = Inventory.lookupPart(searchText);
        // defining out the error here by using regex to check for int:
        if (checkValidInt(searchText)) {
            int partId = Integer.parseInt(searchText);
            Part part = Inventory.lookupPart(partId);
            // will only show if there's a matching part name or a matching part id. Not both.
            if (part != null && parts.size() == 0) {
                parts.add(part);
            }
        }
        return parts;
    }

    /**
     * Helper method to search for products by name or id.
     *
     * @param searchText the text to search for
     * @return the list of products
     */
    public static ObservableList<Product> searchProducts(String searchText) {
        ObservableList<Product> products = Inventory.lookupProduct(searchText);
        // defining out the error here by using regex to check for int:
        if (checkValidInt(searchText)) {
            int productId = Integer.parseInt(searchText);
            Product product = Inventory.lookupProduct(productId);
            // will only show if there's a matching product name or a matching product id. Not both.
            if (product != null && products.size() == 0) {
                products.add(product);
            }
        }
        return products;
    }

    public static boolean checkValidInt(String str){
        return str.matches("^-?\\d+$");
    }

    public static void showErrorDialog(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Program error.");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showWarningDialog(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Continue?");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean checkValidDouble(String str){
        return str.matches("^[-+]?\\d*\\.?\\d+$");
    }
}
