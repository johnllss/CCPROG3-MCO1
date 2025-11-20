package StoreApp.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Employee_Login_Controller {
    @FXML private TextField emailText;
    @FXML private TextField passwordText;
    @FXML private Label errorLabel;
    @FXML private Button loginBtn;
    @FXML private Button returnBtn;

    public void login()
    {
        System.out.println("Login");
    }

    public void returnToMainMenu()
    {
        System.out.println("Return to Main Menu");
    }
}
