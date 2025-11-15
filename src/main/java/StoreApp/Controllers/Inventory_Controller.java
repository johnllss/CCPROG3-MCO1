package StoreApp.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class Inventory_Controller implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;

    private String[] categories = {"food", "beverage", "medicine", "household"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(categories);
    }

    public void getCategory(ActionEvent event)
    {
        String choice = choiceBox.getValue();

    }
}
