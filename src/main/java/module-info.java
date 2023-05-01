module rhettdelfierro.c482 {
    requires javafx.controls;
    requires javafx.fxml;


    opens rhettdelfierro.c482 to javafx.fxml;

    exports rhettdelfierro.c482;
    exports rhettdelfierro.c482.controllers;
    exports rhettdelfierro.c482.models;
    opens rhettdelfierro.c482.controllers to javafx.fxml;
}