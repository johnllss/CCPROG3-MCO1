package StoreApp.Controllers;

import StoreApp.Models.Employee_Model;
import StoreApp.Models.Inventory_Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu_Controller {
    private Employee_Model[] employees;
    private Inventory_Model inventory;

    // setters for injections
    public void setEmployees(Employee_Model[] employees)
    {
        this.employees = employees;
    }

    public void setInventory(Inventory_Model inventory)
    {
        this.inventory = inventory;
    }

    @FXML
    public void goToCustomerView(ActionEvent event)
    {
        System.out.println("Customer View");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Shopping_View.fxml"));
            Parent root = loader.load();

            Shopping_Controller shoppingController = loader.getController();

            shoppingController.setInventory(inventory);

            // code for switching fxml
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToEmployeeView(ActionEvent event)
    {
        System.out.println("Employee View");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Employee_Login_View.fxml"));
            Parent root = loader.load();

            Employee_Login_Controller loginController = loader.getController();

            loginController.setEmployees(employees);
            loginController.setInventory(inventory);

            // code for switching fxml
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToExit(ActionEvent event)
    {
        System.out.println("Exiting...");
        System.exit(0);
    }
}
