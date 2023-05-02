package rhettdelfierro.c482;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rhettdelfierro.c482.models.InHouse;
import rhettdelfierro.c482.models.Inventory;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/rhettdelfierro/c482/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 530);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        InHouse part1 = new InHouse(1, "Part 1", 1.99, 1, 1, 1, 1);
        InHouse part2 = new InHouse(2, "Part 2", 2.99, 2, 2, 2, 2);
        InHouse part3 = new InHouse(3, "Part 3", 3.99, 3, 3, 3, 3);
        InHouse part5 = new InHouse(5, "Part 3", 5.99, 5, 1, 4, 5);
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part5);
        Inventory.addPart(part3);


        launch();
    }
}