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
        inventory.addProduct(new Product_Model("Sandwich", 30.0, 10, "Food", "Subway", "Egg and Cheese Ciabatta Breakfast Sandwich", LocalDate.parse("2025-11-26"), "/images/sandwich.jpg"));
        inventory.addProduct(new Product_Model("Pastries", 45.0, 10, "Food", "Starbucks", "Ham & Cheese Croissant", LocalDate.parse("2028-10-26"), "/images/pastries.jpg"));
        inventory.addProduct(new Product_Model("Chicken Nuggets", 50.0, 10, "Food", "Tyson", "Frozen", LocalDate.parse("2026-01-15"), "/images/chickennuggets.jpg"));
        inventory.addProduct(new Product_Model("Sushi", 120.0, 10, "Food", "Maki Mono", "8s", LocalDate.parse("2027-03-10"), "/images/sushi.jpg"));
        inventory.addProduct(new Product_Model("Yogurt", 215.0, 10, "Food", "Chobani", "Greek Yogurt Blueberry", LocalDate.parse("2023-10-27"), "/images/yogurt.jpg"));
        inventory.addProduct(new Product_Model("Pancit Canton", 15.0, 10, "Food", "Lucky Me!", "Kalamansi", LocalDate.parse("2026-08-20"), "/images/pancitcanton.jpg"));
        inventory.addProduct(new Product_Model("Cup Noodles", 12.0, 10, "Food", "Nissin", "Very Veggie Chicken Flavor", LocalDate.parse("2026-11-15"), "/images/cupnoodles.jpg"));
        inventory.addProduct(new Product_Model("Pan de Sal", 25.0, 10, "Food", "Gardenia", "Classic", LocalDate.parse("2025-12-05"), "/images/pandesal.jpg"));
        inventory.addProduct(new Product_Model("Tuyo", 85.0, 10, "Food", "Ligo", "Spanish Style", LocalDate.parse("2027-04-12"), "/images/tuyo.jpg"));
        inventory.addProduct(new Product_Model("Corned Beef", 65.0, 10, "Food", "Purefoods", "Original", LocalDate.parse("2026-09-30"), "/images/cornedbeef.jpg"));

        // Beverages
        inventory.addProduct(new Product_Model("Dairy", 100.0, 3, "Beverages", "Selecta", "Milk", LocalDate.parse("2024-11-30"), "/images/dairy.jpg"));
        inventory.addProduct(new Product_Model("Coffee", 220.0, 0, "Beverages", "Starbucks", "Iced", LocalDate.parse("2025-02-02"), "/images/coffee.jpg"));
        inventory.addProduct(new Product_Model("Tea", 115.0, 1, "Beverages", "Honest Tea", "Green Tea", LocalDate.parse("2026-02-14"), "/images/tea.png"));
        inventory.addProduct(new Product_Model("Energy Drink", 159.0, 999, "Beverages", "Red Bull", "The Summer Edition", LocalDate.parse("2027-10-11"), "/images/redbull.png"));
        inventory.addProduct(new Product_Model("Beer", 90.0, 10, "Beverages", "Stella Artois", "Stella 0.0", LocalDate.parse("2028-08-15"), "/images/beer.png"));
        inventory.addProduct(new Product_Model("Calamansi Juice", 35.0, 10, "Beverages", "Zest-O", "Dalandan", LocalDate.parse("2026-06-18"), "/images/calamansi.jpg"));
        inventory.addProduct(new Product_Model("Soda", 45.0, 10, "Beverages", "Fanta", "330ml", LocalDate.parse("2025-12-25"), "/images/soda.jpg"));
        inventory.addProduct(new Product_Model("Iced Tea", 28.0, 10, "Beverages", "Nestea", "Lemon", LocalDate.parse("2026-03-08"), "/images/icedtea.jpg"));
        inventory.addProduct(new Product_Model("Chocolate Drink", 55.0, 10, "Beverages", "Milo", "Ready to Drink", LocalDate.parse("2025-11-30"), "/images/chocolatedrink.jpg"));
        inventory.addProduct(new Product_Model("Coconut Water", 40.0, 10, "Beverages", "C2O", "Cool & Clean", LocalDate.parse("2026-07-22"), "/images/coconutwater.jpg"));

        // Toiletries
        inventory.addProduct(new Product_Model("Soap", 50.0, 1, "Toiletries", "Safeguard", "Pink", null, "/images/soap.jpg"));
        inventory.addProduct(new Product_Model("Shampoo", 99.0, 10, "Toiletries", "Head & Shoulders", "Cool Menthol", null, "/images/shampoo.jpg"));
        inventory.addProduct(new Product_Model("Airy Matte Tint", 30.0, 10, "Toiletries", "BLK Cosmetics", "Dearest Rose Pink", null, "/images/airymattetint.jpg"));
        inventory.addProduct(new Product_Model("Toothbrush", 135.0, 10, "Toiletries", "Oral-B", "Electric Toothbrush", null, "/images/toothbrush.jpg"));
        inventory.addProduct(new Product_Model("Deodorant", 199.0, 10, "Toiletries", "Old Spice", "Wolfthorne", null, "/images/deodorant.jpg"));
        inventory.addProduct(new Product_Model("Toothpaste", 75.0, 10, "Toiletries", "Colgate", "Total Clean", null, "/images/toothpaste.jpg"));
        inventory.addProduct(new Product_Model("Conditioner", 120.0, 10, "Toiletries", "Palmolive", "Naturals Aloe Vera", null, "/images/conditioner.png"));
        inventory.addProduct(new Product_Model("Facial Wash", 95.0, 10, "Toiletries", "Cetaphil", "Gentle Skin Cleanser", null, "/images/facialwash.jpg"));
        inventory.addProduct(new Product_Model("Baby Powder", 68.0, 10, "Toiletries", "Johnson's", "Classic", null, "/images/babypowder.jpeg"));
        inventory.addProduct(new Product_Model("Petroleum Jelly", 45.0, 10, "Toiletries", "Vaseline", "Original", null, "/images/petroleumjelly.png"));

        // Cleaning Products
        inventory.addProduct(new Product_Model("Detergent", 55.0, 3, "Cleaning Products", "Tide", "Original Fresh", LocalDate.parse("2027-01-02"), "/images/detergent.jpg"));
        inventory.addProduct(new Product_Model("Tissue", 69.0, 10, "Cleaning Products", "Femme", "Interfolded Paper Towel", LocalDate.parse("2027-03-25"), "/images/tissue.png"));
        inventory.addProduct(new Product_Model("Hand Sanitizer", 95.0, 10, "Cleaning Products", "Purell", "Bottled", LocalDate.parse("2029-09-23"), "/images/handsanitizer.jpg"));
        inventory.addProduct(new Product_Model("Degreaser", 215.0, 10, "Cleaning Products", "Lysol", "Multi-Purpose", LocalDate.parse("2028-11-11"), "/images/degreaser.jpg"));
        inventory.addProduct(new Product_Model("Bleach", 150.0, 10, "Cleaning Products", "Zonrox", "Original", LocalDate.parse("2023-11-05"), "/images/bleach.jpg"));
        inventory.addProduct(new Product_Model("Dishwashing Liquid", 42.0, 10, "Cleaning Products", "Joy", "Lemon", LocalDate.parse("2027-05-10"), "/images/dishwashingliquid.jpg"));
        inventory.addProduct(new Product_Model("Fabric Conditioner", 85.0, 10, "Cleaning Products", "Downy", "Sunrise Fresh", LocalDate.parse("2026-12-20"), "/images/fabricconditioner.jpg"));
        inventory.addProduct(new Product_Model("Floor Wax", 180.0, 10, "Cleaning Products", "Johnson", "Paste Wax", LocalDate.parse("2028-02-14"), "/images/floorwax.jpg"));
        inventory.addProduct(new Product_Model("Glass Cleaner", 110.0, 10, "Cleaning Products", "Windex", "Original", LocalDate.parse("2027-08-05"), "/images/glasscleaner.jpg"));
        inventory.addProduct(new Product_Model("Air Freshener", 125.0, 10, "Cleaning Products", "Glade", "Lavender", LocalDate.parse("2026-10-18"), "/images/airfreshener.png"));

        // Medications
        inventory.addProduct(new Product_Model("Adhesive Bandage", 25.0, 10, "Medications", "Band-Aid", "Waterproof", LocalDate.parse("2026-05-17"), "/images/adhesivebandage.jpg"));
        inventory.addProduct(new Product_Model("Antibiotic", 30.0, 10, "Medications", "Amoxil", "Amoxicillin", LocalDate.parse("2026-01-23"), "/images/antibiotic.jpg"));
        inventory.addProduct(new Product_Model("Antihistamine", 80.0, 10, "Medications", "Claritin", "Standard Tablet", null, "/images/antihistamine.png"));
        inventory.addProduct(new Product_Model("Anesthetics", 125.0, 10, "Medications", "Diprivan", "Propofol", null, "/images/anesthetics.jpg"));
        inventory.addProduct(new Product_Model("Paracetamol", 55.0, 10, "Medications", "Biogesic", "500mg", LocalDate.parse("2028-08-15"), "/images/paracetamol.png"));
        inventory.addProduct(new Product_Model("Cough Syrup", 95.0, 10, "Medications", "Robitussin", "Multi-Symptom", LocalDate.parse("2027-02-28"), "/images/coughsyrup.jpg"));
        inventory.addProduct(new Product_Model("Antacid", 48.0, 10, "Medications", "Kremil-S", "Original", LocalDate.parse("2026-09-12"), "/images/antacid.jpg"));
        inventory.addProduct(new Product_Model( "Vitamin C", 150.0, 10, "Medications", "Enervon-C", "500mg", LocalDate.parse("2028-03-20"), "/images/vitamincc.jpg"));
        inventory.addProduct(new Product_Model("Pain Reliever", 65.0, 10, "Medications", "Alaxan", "FR", LocalDate.parse("2027-11-08"), "/images/painreliever.png"));
        inventory.addProduct(new Product_Model("Antiseptic", 88.0, 10, "Medications", "Betadine", "Solution", LocalDate.parse("2029-01-15"), "/images/antiseptic.png"));

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
