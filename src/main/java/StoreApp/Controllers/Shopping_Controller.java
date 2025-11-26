package StoreApp.Controllers;

import StoreApp.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.ArrayList;

public class Shopping_Controller {
    @FXML GridPane productsGrid;
    @FXML Text categoryLabel;
    @FXML Button cartBtn;
    @FXML Button foodBtn;
    @FXML Button beverageBtn;
    @FXML Button toiletriesBtn;
    @FXML Button cleaningProductsBtn;
    @FXML Button medicineBtn;
    @FXML Button backBtn;
    @FXML Button previousCategoryBtn;
    @FXML Button nextCategoryBtn;

    private Stage primaryStage;
    private Scene scene;
    private Parent root;

    private Inventory_Model inventory;
    private Customer_Model customer;
    private Employee_Model[] employees;

    private String currentCategory = "Food";
    private final String[] categories = {"Food", "Beverages", "Toiletries", "Cleaning Products", "Medications"};
    private int currentCategoryIndex = 0;
    private boolean isInitialized = false;

    /**
     * This method initializes the controller after FXML elements are loaded.
     */
    @FXML
    public void initialize()
    {
        isInitialized = true;
        categoryLabel.setText(currentCategory);
        setupNavBar();
        // Try to populate if both customer and inventory are already set
        if (inventory != null && customer != null && productsGrid != null)
        {
            populateProductsGrid();
        }
    }

    /**
     * This method populates the products grid with products from the selected category.
     */
    private void populateProductsGrid()
    {
        if (inventory == null || productsGrid == null || !isInitialized) 
        {
            return;
        }
        
        // clear out products
        productsGrid.getChildren().clear();
        
        // get products of customer's chosen category
        ArrayList<Product_Model> products = inventory.getProductsByCategory(currentCategory);

        // filter out expired products and do not show them in the product grid
        products.removeIf(product -> product.isExpired());

        int col = 0;
        int row = 0;
        
        // create a product card until reaching 4 columns
        for (Product_Model product : products)
            {
            try
            {
                VBox productCard = createProductCard(product);
                productsGrid.add(productCard, col, row);
                
                col++;
                
                // If 4 cols, then go down +1 row
                if (col >= 4)
                    {
                        col = 0;
                        row++;
                    }
                }
                catch (IOException e)
                {
                    System.out.println("Failed to load product card: " + e.getMessage());
                }
            }
        }
        
        
    /**
     * This method creates a product card UI component for a given product.
     * @param product is the product to create a card for.
     * @return VBox containing the product card UI.
     * @throws IOException if the FXML file cannot be loaded.
     */
    private VBox createProductCard(Product_Model product) throws IOException
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Product_View.fxml"));
            VBox card = loader.load();

            Product_Controller productController = loader.getController();
            
            Item_Model cartItem = customer.getCart().findItem(product.getProductID());
            int existingQty = cartItem != null ? cartItem.getQuantity() : 0;

            productController.setProduct(product, existingQty, this::handleAddToCart);

            return card;
        } catch (Exception e) {
            System.err.println("Error loading product card for " + product.getProductName());
            e.printStackTrace();
            throw new IOException("Failed to load Product_View.fxml", e);
        }
    }
    
    /**
     * This method sets up the navigation bar with category button actions.
     */
    private void setupNavBar()
    {
        foodBtn.setOnAction(e -> switchCategory("Food"));
        beverageBtn.setOnAction(e -> switchCategory("Beverages"));
        toiletriesBtn.setOnAction(e -> switchCategory("Toiletries"));
        cleaningProductsBtn.setOnAction(e -> switchCategory("Cleaning Products"));
        medicineBtn.setOnAction(e -> switchCategory("Medications"));
        previousCategoryBtn.setOnAction(e -> navigateToPreviousCategory());
        nextCategoryBtn.setOnAction(e -> navigateToNextCategory());
    }

    /**
     * This method switches the displayed category and updates the products grid.
     * @param category is the category to switch to.
     */
    private void switchCategory(String category)
    {
        currentCategory = category;
        categoryLabel.setText(category);
        
        // update current category index to match the selected category (to let the previous and next category buttons know which category is currently selected)
        for (int i = 0; i < categories.length; i++)
            {
                if (categories[i].equals(category))
                {
                    currentCategoryIndex = i;
                    break;
                }
            }
            
        populateProductsGrid();
    }
                
    /**
     * This method navigates to the previous category in the category list.
     */
    private void navigateToPreviousCategory()
    {
        currentCategoryIndex--;

        // wrap around to the last category if index goes < 0
        if (currentCategoryIndex < 0)
            {
                currentCategoryIndex = categories.length - 1;
            }

        switchCategory(categories[currentCategoryIndex]);
    }

    /**
     * This method navigates to the next category in the category list.
     */
    private void navigateToNextCategory()
    {
        currentCategoryIndex++;
        
        // wrap around to the first category if index exceeds the category array length
        if (currentCategoryIndex >= categories.length)
        {
            currentCategoryIndex = 0;
        }
            
        switchCategory(categories[currentCategoryIndex]);
    }
        
    /**
     * This method handles navigation back to the main menu.
     * @param e is the action event triggered by the back button.
     * @throws IOException if the FXML file cannot be loaded.
     */
    @FXML
    public void backToMain(ActionEvent event) throws IOException
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainMenu_View.fxml"));
            root = loader.load();
            
            // pass inventory and employees back to MainMenu
            MainMenu_Controller mainMenuController = loader.getController();
            mainMenuController.setInventory(inventory);
            mainMenuController.setEmployees(employees);
            
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    /**
     * This method handles adding a product to the cart or updating its quantity.
     * @param product is the product to add or update.
     * @param quantity is the quantity to add.
     */
    private void handleAddToCart(Product_Model product, int quantity)
    {
        if (customer == null)
        {
            System.err.println("error: Customer not set in Shopping_Controller");
            return;
        }
            
        Cart_Model cart = customer.getCart();
        Item_Model item = customer.getCart().findItem(product.getProductID());

        if (item != null) {
        // item exists in cart, so increment its quantity
            if (cart.incrementQuantity(product, quantity)) {
                System.out.println("Added " + quantity + "x to " + product.getProductName() + ", total now: " + cart.findItem(product.getProductID()).getQuantity());
            } else {
                System.err.println("Failed to increment quantity for " + product.getProductName());
            }
        // item doesn't exist in the cart, add it to cart
        } else {
            if (cart.addItem(product, quantity)) {
                System.out.println("Added " + quantity + "x " + product.getProductName() + " to cart");
            } else {
                System.err.println("Failed to add " + product.getProductName() + " to cart");
            }
        }
    }
    
    /**
     * This method handles navigation to the Cart_View.
     * @param event is the action event triggered by the cart button.
     */
    @FXML
    public void goToCart(ActionEvent event)
    {
        try
        {
            // load FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Cart_View.fxml"));
            Parent root = loader.load();

            // get controller and pass inventory and customer states
            Cart_Controller cartController = loader.getController();
            cartController.setInventory(inventory);
            cartController.setCustomer(customer);
            cartController.setEmployees(employees);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * This sets the inventory for the controller.
     * @param inv is the Inventory_Model to be set.
     */
    public void setInventory(Inventory_Model inv)
    {
        this.inventory = inv;
        if(customer != null && isInitialized && productsGrid != null)
        {
            populateProductsGrid();
        }
    }

    /**
     * This sets the customer for the controller.
     * @param customer is the Customer_Model to be set.
     */
    public void setCustomer(Customer_Model customer)
    {
        this.customer = customer;
        if(inventory != null && isInitialized && productsGrid != null)
        {
            populateProductsGrid();
        }
    }

    /**
     * This sets the employees array for the controller.
     * @param employees is the array of Employee_Model to be set.
     */
    public void setEmployees(Employee_Model[] employees)
    {
        this.employees = employees;
    }
}