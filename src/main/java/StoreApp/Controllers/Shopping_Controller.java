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

import StoreApp.Models.Inventory_Model;
import StoreApp.Models.Product_Model;
import StoreApp.Views.Product_View;

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
    private String currentCategory = "Beverages";

    private Stage primaryStage;
    private Scene scene;
    private Parent root;

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
            Product_View productCard = createProductCard(product);
            productsGrid.add(productCard, col, row);

            col++;

            // If 4 cols, then go down +1 row
            if (col >= 4)
            {
                col = 0;
                row++;
            }
        }
    }

    private Product_View createProductCard(Product_Model product)
    {
        return new Product_View(product, this::handleAddToCart);
    }

    private void handleAddToCart(Product_Model product)
    {
        System.out.println("Adding to cart: " + product.getProductName());
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

}
