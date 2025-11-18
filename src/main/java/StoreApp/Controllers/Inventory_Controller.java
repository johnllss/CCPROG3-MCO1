package StoreApp.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

public class Inventory_Controller implements Initializable {

    @FXML private ChoiceBox<String> choiceBox;
    @FXML private Button logout_btn;
    @FXML private AnchorPane scenePane;

    private Stage stage;

    private String[] categories = {"food", "beverage", "medicine", "household"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(categories);
    }

    public void getCategory(ActionEvent event)
    {
        String choice = choiceBox.getValue();
    }

    public void logout(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout.");
        alert.setContentText("Do you want to save before exiting?");

        if (alert.showAndWait().get() == ButtonType.OK)
        {
            stage = (Stage)scenePane.getScene().getWindow();
            System.out.println("You successfully logged out!");
            stage.close();
        }
    }
}
