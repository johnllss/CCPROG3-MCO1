package StoreApp.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Employee_Login_Controller {
    @FXML private TextField emailText;
    @FXML private TextField passwordText;
    @FXML private Label errorLabel;
    @FXML private Button loginBtn;
    @FXML private Button returnBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event) throws IOException
    {
        String employeeEmail = emailText.getText();

        FXMLLoader loader = new FXMLLoader("Inventory_View.fxml");
        root = loader.load();

        Inventory_Controller inventoryController = loader.getController();

        // TODO: method to be implemented
        inventoryController.displayEmployeeName(employeeName);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void
    returnToMainMenu()
    {
        // TODO: implement returning to main menu
    }
}
