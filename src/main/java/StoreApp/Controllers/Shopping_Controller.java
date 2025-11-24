package StoreApp.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.ArrayList;

import StoreApp.Models.Cart_Model;
import StoreApp.Models.Customer_Model;
import StoreApp.Models.Inventory_Model;
import StoreApp.Models.Product_Model;
import javafx.scene.layout.AnchorPane;

public class Shopping_Controller {
    @FXML GridPane productsGrid;
    @FXML Label categoryLabel;
    @FXML Button cartBtn;
    @FXML Button foodBtn;
    @FXML Button beverageBtn;
    @FXML Button toiletriesBtn;
    @FXML Button medicineBtn;
    @FXML Button householdBtn;
    @FXML Button backBtn;

    private Inventory_Model inventory;
    private Customer_Model customer;
    private String currentCategory = "Beverages";

    private Stage primaryStage;
    private Scene scene;
    private Parent root;
    private Product_Model productModel;

    @FXML
    public void initialize()
    {
        categoryLabel.setText(currentCategory);
        setupNavBar();
    }

    public void setInventory(Inventory_Model inv)
    {
        this.inventory = inv;
        populateProductsGrid();
    }

    public void setCustomer(Customer_Model customer)
    {
        this.customer = customer;
    }

    private void populateProductsGrid()
    {
        if (inventory == null) 
        {
            return;
        }

        // clear out products
        productsGrid.getChildren().clear();

        // get products of customer's chosen category
        ArrayList<Product_Model> products = inventory.getProductsByCategory(currentCategory);

        int col = 0;
        int row = 0;

        // create a product card until reaching 4 columns
        for (Product_Model product : products)
        {
            try
            {
                AnchorPane productCard = createProductCard(product);
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

    private AnchorPane createProductCard(Product_Model product) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ProductCard.fxml"));
        AnchorPane card = loader.load();
        Product_Controller productController = loader.getController();
        productController.setProduct(product, this::handleAddToCart);
        return card;
    }

    private void setupNavBar()
    {
        foodBtn.setOnAction(e -> switchCategory("Food"));
        beverageBtn.setOnAction(e -> switchCategory("Beverages"));
        toiletriesBtn.setOnAction(e -> switchCategory("Toiletries"));
        medicineBtn.setOnAction(e -> switchCategory("Medications"));
        householdBtn.setOnAction(e -> switchCategory("Cleaning Products"));
    }

    private void switchCategory(String category)
    {
        currentCategory = category;
        categoryLabel.setText(category.toUpperCase());
        populateProductsGrid();
    }

    @FXML
    public void backToMain(ActionEvent e) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("/View/MainMenu_View.fxml"));
        primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleAddToCart(Product_Model product, int quantity)
    {
        if (customer == null)
        {
            System.err.println("error: Customer not set in Shopping_Controller");
            return;
        }

        Cart_Model cart = customer.getCart();
        boolean success = cart.addItem(product, quantity);

        if (success)
        {
            System.out.println("Added " + quantity + "x " + product.getProductName() + " to cart");
        }
        else
        {
            System.err.println("Item has not been successfully added to cart.");
        }
    }

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
}