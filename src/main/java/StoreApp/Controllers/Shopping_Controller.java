package StoreApp.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Shopping_Controller {
    @FXML Label productsGrid;
    @FXML Label categoryLabel;
    @FXML Button cartBtn;
    @FXML Button foodBtn;
    @FXML Button beverageBtn;
    @FXML Button toiletriesBtn;
    @FXML Button medicineBtn;
    @FXML Button householdBtn;

    @FXML
    public void initialize()
    {
        categoryLabel.setText("Beverages"); // TODO: modify to be dynamic
        populateProductsGrid();
        setupNavBar();
    }

    private void populateProductsGrid()
    {
        // TODO
        /*
            1. extract product names
            2. extract product prices
            3. get image paths
            4. for loop for creating a Product Card display
                - .add() to add the product to the productGrid

            Note: Product Card display might need a Product_View.java to instantiate
         */
    }

    private void setupNavBar()
    {
        // fxmlFile should use Product_View or perhaps just create a .fxml view for each category?
        foodBtn.setOnAction(e -> switchCategory(""));
        beverageBtn.setOnAction(e -> switchCategory(""));
        toiletriesBtn.setOnAction(e -> switchCategory(""));
        medicineBtn.setOnAction(e -> switchCategory(""));
        householdBtn.setOnAction(e -> switchCategory(""));
    }

    private void switchCategory(String fxmlFile)
    {
        System.out.println("Navigating to " + fxmlFile);
        // TODO:
        /*
            1. load FXML using FXMLLoader
            2. set this in the main stage
         */
    }
}
