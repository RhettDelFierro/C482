package rhettdelfierro.c482.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
}
