package StoreApp;

import StoreApp.Controllers.MainMenu_Controller;
import StoreApp.Models.Employee_Model;
import StoreApp.Models.Inventory_Model;
import StoreApp.Models.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // declare core data
            Inventory_Model inventory = new Inventory_Model();
            Employee_Model[] employees;

            if (!initializeInventory(inventory))
            {
                System.err.println("error: inventory has not been successfully initialized");
            }

            employees = initializeEmployees();

            // load main menu view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainMenu_View.fxml"));
            Parent root = loader.load();

            // inject data into MainMenu
            MainMenu_Controller mainMenuController = loader.getController();
            mainMenuController.setEmployees(employees);
            mainMenuController.setInventory(inventory);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("StoreApp");
            stage.show();

            stage.setOnCloseRequest(event -> {
                event.consume(); // prevents from closing the program when pressing the X button
                logout(stage);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean initializeInventory(Inventory_Model inventory)
    {
        if (inventory == null)
        {
            System.err.println("error: uninitializable null inventory");
            return false;
        }

        // INITIALIZATION OF INVENTORY'S PRODUCTS
        // Food
        inventory.addProduct(new Product("Sandwich", 30.0, 10, "Food", "Subway", "Cheese", "2025-10-27"));
        inventory.addProduct(new Product("Pastries", 45.0, 10, "Food", "Subway", "Cheese", "2025-10-26"));
        inventory.addProduct(new Product("Fried Chicken", 50.0, 10, "Food", "Subway", "Cheese", "2026-01-15"));
        inventory.addProduct(new Product("Sushi", 120.0, 10, "Food", "Makimura Ramen Bar", "Raw", "2027-03-10"));
        inventory.addProduct(new Product("Yogurt", 215.0, 10, "Food", "Chobani", "Vanilla", "2025-10-27"));

        // Beverages
        inventory.addProduct(new Product("Dairy", 100.0, 10, "Beverages", "Selecta", "Milk", "2025-11-30"));
        inventory.addProduct(new Product("Coffee", 220.0, 10, "Beverages", "Starbucks", "Iced", "2025-02-02"));
        inventory.addProduct(new Product("Tea", 115.0, 10, "Beverages", "Honest Tea", "Green Tea", "2026-02-14"));
        inventory.addProduct(new Product("Energy Drink", 159.0, 10, "Beverages", "Red Bull", "The Summer Edition", "2027-10-11"));
        inventory.addProduct(new Product("Beer", 90.0, 10, "Beverages", "Stella Artois", "Stella 0.0", "2028-08-15"));

        // Toiletries
        inventory.addProduct(new Product("Soap", 50.0, 10, "Toiletries", "Safeguard", "Pink", "N/A"));
        inventory.addProduct(new Product("Shampoo", 99.0, 10, "Toiletries", "Head & Shoulders", "Classic Clean", "N/A"));
        inventory.addProduct(new Product("Airy Matte Tint", 30.0, 10, "Toiletries", "BLK Cosmetics", "Dearest Rose Pink", "N/A"));
        inventory.addProduct(new Product("Toothbrush", 135.0, 10, "Toiletries", "Oral-B", "Electric Toothbrush", "N/A"));
        inventory.addProduct(new Product("Deodorant", 199.0, 10, "Toiletries", "Old Spice", "Wolfthorne", "N/A"));

        // Cleaning Products
        inventory.addProduct(new Product("Detergent", 55.0, 10, "Cleaning Products", "Tide", "Powder", "2027-01-02"));
        inventory.addProduct(new Product("Tissue", 69.0, 10, "Cleaning Products", "Femme", "Interfolded Paper Towel", "2027-03-25"));
        inventory.addProduct(new Product("Hand Sanitizer", 95.0, 10, "Cleaning Products", "Purell", "Bottled", "2029-09-23"));
        inventory.addProduct(new Product("Degreaser", 215.0, 10, "Cleaning Products", "Lysol", "Multi-Purpose", "2028-11-11"));
        inventory.addProduct(new Product("Bleach", 150.0, 10, "Cleaning Products", "Zonrox", "Original", "2023-11-05"));

        // Medications
        inventory.addProduct(new Product("Adhesive Bandage", 25.0, 10, "Medications", "Band-Aid", "Waterproof", "2026-05-17"));
        inventory.addProduct(new Product("Antibiotic", 30.0, 10, "Medications", "Amoxil", "Amoxicillin", "2026-01-23"));
        inventory.addProduct(new Product("Antihistamine", 80.0, 10, "Medications", "Claritin", "Standard Tablet", "N/A"));
        inventory.addProduct(new Product("Anesthetics", 125.0, 10, "Medications", "Diprivan", "Propofol", "N/A"));
        inventory.addProduct(new Product("Paracetamol", 55.0, 10, "Medications", "Biogesic", "500mg", "2028-08-15"));

        // debugger
        System.out.println("Inventory is successfully initialized.");
        return true;
    }

    private Employee_Model[] initializeEmployees()
    {
        return new Employee_Model[] {
                new Employee_Model("John Lloyd", "johnlloyd@gmail.com", "abc123", "Manager"),
                new Employee_Model("Kristin", "kristin@gmail.com", "xyz456", "Manager"),
                new Employee_Model("Leon", "leon@gmail.com", "lmn098", "Restocker")
        };
    }

    public void logout(Stage stage)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout!");
        alert.setContentText("Do you want to save before exiting?: ");

        if (alert.showAndWait().get() == ButtonType.OK)
        {
            System.out.println("You successfully logged out!");
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
