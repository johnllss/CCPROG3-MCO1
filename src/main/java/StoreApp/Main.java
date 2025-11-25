package StoreApp;

import StoreApp.Controllers.MainMenu_Controller;
import StoreApp.Models.Employee_Model;
import StoreApp.Models.Inventory_Model;
import StoreApp.Models.Product_Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {
    /**
     * This method starts the JavaFX application and initializes the main menu.
     * @param stage is the primary stage for this application.
     */
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

    /**
     * This method initializes the inventory with sample products.
     * @param inventory is the Inventory_Model to be initialized.
     * @return boolean for success/failure.
     */
    private boolean initializeInventory(Inventory_Model inventory)
    {
        if (inventory == null)
        {
            System.err.println("error: uninitializable null inventory");
            return false;
        }

        // INITIALIZATION OF INVENTORY'S PRODUCTS
        // Food
        inventory.addProduct(new Product_Model("Sandwich", 30.0, 10, "Food", "Subway", "Cheese", LocalDate.parse("2025-10-27"), "/redbull.png"));
        inventory.addProduct(new Product_Model("Pastries", 45.0, 10, "Food", "Subway", "Cheese", LocalDate.parse("2025-10-26"), "/redbull.png"));
        inventory.addProduct(new Product_Model("Fried Chicken", 50.0, 10, "Food", "Subway", "Cheese", LocalDate.parse("2026-01-15"), "/redbull.png"));
        inventory.addProduct(new Product_Model("Sushi", 120.0, 10, "Food", "Makimura Ramen Bar", "Raw", LocalDate.parse("2027-03-10"), "/redbull.png"));
        inventory.addProduct(new Product_Model("Yogurt", 215.0, 10, "Food", "Chobani", "Vanilla", LocalDate.parse("2025-10-27"), "/redbull.png"));

        // Beverages
        inventory.addProduct(new Product_Model("Dairy", 100.0, 10, "Beverages", "Selecta", "Milk", LocalDate.parse("2025-11-30"), "/redbull.png"));
        inventory.addProduct(new Product_Model("Coffee", 220.0, 10, "Beverages", "Starbucks", "Iced", LocalDate.parse("2025-02-02"), "/redbull.png"));
        inventory.addProduct(new Product_Model("Tea", 115.0, 10, "Beverages", "Honest Tea", "Green Tea", LocalDate.parse("2026-02-14"), "/redbull.png"));
        inventory.addProduct(new Product_Model("Energy Drink", 159.0, 10, "Beverages", "Red Bull", "The Summer Edition", LocalDate.parse("2027-10-11"), "/redbull.png"));
        inventory.addProduct(new Product_Model("Beer", 90.0, 10, "Beverages", "Stella Artois", "Stella 0.0", LocalDate.parse("2028-08-15"), "/redbull.png"));

        // Toiletries
        inventory.addProduct(new Product_Model("Soap", 50.0, 1, "Toiletries", "Safeguard", "Pink", null, "/redbull.png"));
        inventory.addProduct(new Product_Model("Shampoo", 99.0, 10, "Toiletries", "Head & Shoulders", "Classic Clean", null, "/redbull.png"));
        inventory.addProduct(new Product_Model("Airy Matte Tint", 30.0, 10, "Toiletries", "BLK Cosmetics", "Dearest Rose Pink", null, "/redbull.png"));
        inventory.addProduct(new Product_Model("Toothbrush", 135.0, 10, "Toiletries", "Oral-B", "Electric Toothbrush", null, "/redbull.png"));
        inventory.addProduct(new Product_Model("Deodorant", 199.0, 10, "Toiletries", "Old Spice", "Wolfthorne", null, "/redbull.png"));

        // Cleaning Products
        inventory.addProduct(new Product_Model("Detergent", 55.0, 3, "Cleaning Products", "Tide", "Powder", LocalDate.parse("2027-01-02"), "/redbull.png"));
        inventory.addProduct(new Product_Model("Tissue", 69.0, 10, "Cleaning Products", "Femme", "Interfolded Paper Towel", LocalDate.parse("2027-03-25"), "/redbull.png"));
        inventory.addProduct(new Product_Model("Hand Sanitizer", 95.0, 10, "Cleaning Products", "Purell", "Bottled", LocalDate.parse("2029-09-23"), "/redbull.png"));
        inventory.addProduct(new Product_Model("Degreaser", 215.0, 10, "Cleaning Products", "Lysol", "Multi-Purpose", LocalDate.parse("2028-11-11"), "/redbull.png"));
        inventory.addProduct(new Product_Model("Bleach", 150.0, 10, "Cleaning Products", "Zonrox", "Original", LocalDate.parse("2023-11-05"), "/redbull.png"));

        // Medications
        inventory.addProduct(new Product_Model("Adhesive Bandage", 25.0, 10, "Medications", "Band-Aid", "Waterproof", LocalDate.parse("2026-05-17"), "/redbull.png"));
        inventory.addProduct(new Product_Model("Antibiotic", 30.0, 10, "Medications", "Amoxil", "Amoxicillin", LocalDate.parse("2026-01-23"), "/redbull.png"));
        inventory.addProduct(new Product_Model("Antihistamine", 80.0, 10, "Medications", "Claritin", "Standard Tablet", null, "/redbull.png"));
        inventory.addProduct(new Product_Model("Anesthetics", 125.0, 10, "Medications", "Diprivan", "Propofol", null, "/redbull.png"));
        inventory.addProduct(new Product_Model("Paracetamol", 55.0, 10, "Medications", "Biogesic", "500mg", LocalDate.parse("2028-08-15"), "/redbull.png"));

        // debugger
        System.out.println("Inventory is successfully initialized.");
        return true;
    }

    /**
     * This method initializes the employees array with sample employees.
     * @return Employee_Model array containing the initialized employees.
     */
    private Employee_Model[] initializeEmployees()
    {
        return new Employee_Model[] {
                new Employee_Model("John Lloyd", "johnlloyd@gmail.com", "abc123", "Manager"),
                new Employee_Model("Kristin", "kristin@gmail.com", "xyz456", "Manager"),
                new Employee_Model("Leon", "leon@gmail.com", "lmn098", "Restocker")
        };
    }

    /**
     * This method displays a logout confirmation dialog and closes the application.
     * @param stage is the stage to be closed.
     */
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


    /**
     * This is the main entry point for the application.
     * @param args is the command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
