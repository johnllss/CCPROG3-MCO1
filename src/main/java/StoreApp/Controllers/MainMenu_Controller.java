package StoreApp.Controllers;

import StoreApp.Models.Cart_Model;
import StoreApp.Models.Customer_Model;
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

    /**
     * This sets the employees array for the controller.
     * @param employees is the array of Employee_Model to be set.
     */
    public void setEmployees(Employee_Model[] employees)
    {
        this.employees = employees;
    }

    /**
     * This sets the inventory for the controller.
     * @param inventory is the Inventory_Model to be set.
     */
    public void setInventory(Inventory_Model inventory)
    {
        this.inventory = inventory;
    }

    /**
     * This method handles navigation to the customer shopping view.
     * @param event is the action event triggered by the button click.
     */
    @FXML
    public void goToCustomerView(ActionEvent event)
    {
        System.out.println("Customer View");

        try {
            // create a new customer with an empty cart for this shopping session
            Customer_Model customer = new Customer_Model("Guest", "guest@gmail.com", "", null, false, new Cart_Model());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Shopping_View.fxml"));
            Parent root = loader.load();

            Shopping_Controller shoppingController = loader.getController();

            shoppingController.setCustomer(customer);
            shoppingController.setEmployees(employees);
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

    /**
     * This method handles navigation to the employee login view.
     * @param event is the action event triggered by the button click.
     */
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

    /**
     * This method handles the exit action to close the application.
     * @param event is the action event triggered by the button click.
     */
    @FXML
    public void goToExit(ActionEvent event)
    {
        System.out.println("Exiting...");
        System.exit(0);
    }
}
