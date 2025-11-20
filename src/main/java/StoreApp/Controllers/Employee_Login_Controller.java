package StoreApp.Controllers;

import StoreApp.Models.Employee;
import StoreApp.Models.Inventory;
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

    // data needed for injection
    private Employee[] employees;
    private Inventory inventory;

    // setters for injections
    public void setEmployees(Employee[] employees)
    {
        this.employees = employees;
    }

    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }

    public void login(ActionEvent event) throws IOException
    {
        if (employees == null || inventory == null)
        {
            errorLabel.setText("system error: Data not initialized");
            System.err.print("error: employees or inventory not initialized");
            return;
        }

        // extract entered employee details
        String employeeEmail = emailText.getText();
        String employeePassword = passwordText.getText();

        if (employeeEmail.isEmpty() || employeePassword.isEmpty())
        {
            errorLabel.setText("Please fill in all required fields.");
            return;
        }

        Employee foundEmployee = null;
        boolean isLoggedIn = false;

        // loop through all employees
        for (Employee e: employees)
        {
            // find match
            if (e.login(employeeEmail, employeePassword))
            {
                foundEmployee = e;
                isLoggedIn = true;
                break;
            }
        }

        if (!isLoggedIn)
        {
            errorLabel.setText("Invalid email or password");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Inventory_View.fxml"));
            root = loader.load();

            Inventory_Controller inventoryController = loader.getController();

            inventoryController.displayEmployeeName(foundEmployee.getName());
            inventoryController.setInventory(inventory);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            errorLabel.setText("error: Failed loading inventory view");
            e.printStackTrace();
        }
    }

    @FXML
    public void returnToMainMenu(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainMenu_View.fxml"));
            root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
