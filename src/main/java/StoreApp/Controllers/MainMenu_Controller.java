package StoreApp.Controllers;

import StoreApp.Models.Employee;
import StoreApp.Models.Inventory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu_Controller {
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

    @FXML
    public void goToCustomerView(ActionEvent event)
    {
        System.out.println("Customer View");
    }

    @FXML
    public void goToEmployeeView(ActionEvent event)
    {
        System.out.println("Employee View");

        try {
            FXMLLoader loader = new FXML(getClass().getResource("/View/Employee_Login_View.fxml"));
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
