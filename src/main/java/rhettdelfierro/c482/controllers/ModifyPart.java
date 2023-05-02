package rhettdelfierro.c482.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import rhettdelfierro.c482.models.InHouse;
import rhettdelfierro.c482.models.Inventory;
import rhettdelfierro.c482.models.Outsourced;
import rhettdelfierro.c482.models.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPart implements Initializable {

    @FXML
    private ToggleGroup inHouseOrOutsourced;

    @FXML
    private TextField invTxt;

    @FXML
    private Label machineIDLabel;

    @FXML
    private TextField machineIdTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField minTxt;

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
    }

    /**
     * Action event handler for choosing the In-House Radio Button
     *
     * @param event the action event
     */
    @FXML
    void onActionPartInHouseRBtn(ActionEvent event) {
        machineIDLabel.setText("Machine ID");
    }

    /**
     * Action event handler for clicking the Save Button. This will update and save the part to the inventory
     * data store and reroute the user to the main screen.
     * @param event the action event
     */
    @FXML
    void onActionUpdatePart(ActionEvent event) throws IOException {
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

        int id = Integer.parseInt(partIdTxt.getText());
        String name = partNameTxt.getText();
        int stock = Integer.parseInt(invTxt.getText());
        double price = Double.parseDouble(priceTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        boolean isInHouse = partInHouseRBtn.isSelected();
        boolean isOutsourced = partOutsourcedRBtn.isSelected();
        int index = Helpers.findIndexForPart(id);
        if (isInHouse) {
            if (!Helpers.checkValidInt(machineIdTxt.getText())) {
                Helpers.showErrorDialog("Machine ID must be a number.");
            } else {
                int machineId = Integer.parseInt(machineIdTxt.getText());
                Part newPart = new InHouse(id, name, price, stock, min, max, machineId);
                Inventory.updatePart(index, newPart);
            }

        } else if (isOutsourced) {
            String companyName = machineIdTxt.getText();
            Part newPart = new Outsourced(id, name, price, stock, min, max, companyName);
            Inventory.updatePart(index, newPart);
        }
        Helpers.changeScene(event, "main");
    }

    /**
     * This method is used to load the part data as chosen from the main screen's table view.
     *
     * @param part The part to be loaded into the modify product screen.
     */
    public void sendPart(Part part) {
        partIdTxt.setText(String.valueOf(part.getId()));
        partNameTxt.setText(part.getName());
        invTxt.setText(String.valueOf(part.getStock()));
        priceTxt.setText(String.valueOf(part.getPrice()));
        minTxt.setText(String.valueOf(part.getMin()));
        maxTxt.setText(String.valueOf(part.getMax()));
        if (part instanceof InHouse) {
            machineIdTxt.setText(String.valueOf(((InHouse) part).getMachineId()));
            partInHouseRBtn.setSelected(true);
        } else {
            machineIdTxt.setText(((Outsourced) part).getCompanyName());
            partOutsourcedRBtn.setSelected(true);
        }
    }

    /**
     * Action event handler for clicking the Cancel Button. This will return to the main screen.
     *
     * @param event the action event
     */
    @FXML
    void onActionCancelUpdatePart(ActionEvent event) throws IOException {
        Helpers.changeScene(event, "main");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
