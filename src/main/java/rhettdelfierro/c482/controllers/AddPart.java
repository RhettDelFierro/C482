package rhettdelfierro.c482.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import rhettdelfierro.c482.models.InHouse;
import rhettdelfierro.c482.models.Inventory;
import rhettdelfierro.c482.models.Outsourced;
import rhettdelfierro.c482.models.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for Add Part page.
 *
 * RUNTIME ERROR: parseInt() and parseDouble() would throw an IOException here, but we guard against that by using
 *                the checkValidInt and checkValidDouble helper functions at the top of the methods.
 *
 * FUTURE ENHANCEMENT: Rather than have multiple views and controllers for add/modify part,
 * perhaps can have one view and the controller can dynamically update the fields.
 */
public class AddPart implements Initializable {

    @FXML
    private TextField invTxt;

    @FXML
    private Label machineIDLabel;

    @FXML
    private TextField machineIdTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField partIdTxt;

    @FXML
    private RadioButton partInHouseRBtn;

    @FXML
    private TextField partNameTxt;

    @FXML
    private RadioButton partOutsourcedRBtn;

    /**
     * Action event handler for choosing the Outsource Radio Button
     *
     * @param event the action event
     */
    @FXML
    void onActionOutsourcedRBtn(ActionEvent event) {
        machineIDLabel.setText("Company Name");
        System.out.println("Outsourced Radio Button Clicked");
    }

    /**
     * Action event handler for choosing the In-House Radio Button
     *
     * @param event the action event
     */
    @FXML
    void onActionPartInHouseRBtn(ActionEvent event) {
        machineIDLabel.setText("Machine ID");
        System.out.println("In-House Radio Button Clicked");
    }

    /**
     * Action event handler for clicking the Cancel Button. This will return to the main screen.
     *
     * @param event the action event
     * @throws IOException an IO Exception that bubbles up.
     */
    @FXML
    void onActionCancelAddPart(ActionEvent event) throws IOException {
        Helpers.changeScene(event, "main");
    }

    /**
     * Action event handler for clicking the Save Button. This will save the part to the inventory data store and
     * reroute the user to the main screen.
     *
     * @param event the action event
     * @throws IOException an IOException that is caused if the number parsing throws error.
     */
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
        if (!Helpers.checkValidInt(invTxt.getText())) {
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
        if ((Integer.parseInt(invTxt.getText()) < Integer.parseInt(minTxt.getText())) ||
                (Integer.parseInt(invTxt.getText())) > Integer.parseInt(maxTxt.getText())
        ) {
            Helpers.showErrorDialog("Inventory must be between Min and Max");
            return;
        }
        if (!Helpers.checkValidDouble(priceTxt.getText())) {
            Helpers.showErrorDialog("Price must be a valid amount.");
            return;
        }

        int id = Helpers.getAutoGeneratedPartId();
        String name = partNameTxt.getText();
        int stock = Integer.parseInt(invTxt.getText());
        double price = Double.parseDouble(priceTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        boolean isInHouse = partInHouseRBtn.isSelected();
        boolean isOutsourced = partOutsourcedRBtn.isSelected();

        if (isInHouse) {
            if (!Helpers.checkValidInt(machineIdTxt.getText())) {
                Helpers.showErrorDialog("Machine ID must be a number.");
            } else {
                int machineId = Integer.parseInt(machineIdTxt.getText());
                Part part = new InHouse(id, name, price, stock, min, max, machineId);
                Inventory.addPart(part);
            }

        } else if (isOutsourced) {
            String companyName = machineIdTxt.getText();
            Part part = new Outsourced(id, name, price, stock, min, max, companyName);
            Inventory.addPart(part);
        }

        Helpers.changeScene(event, "main");
    }

    /**
     * Initializes this controller class from implmenting the Initializable interface.
     * @param url the url
     * @param resourceBundle the resource bundle folder
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
