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

public class Shopping_Controller {
    @FXML
    GridPane productsGrid;
    @FXML Label categoryLabel;
    @FXML Button cartBtn;
    @FXML Button foodBtn;
    @FXML Button beverageBtn;
    @FXML Button toiletriesBtn;
    @FXML Button medicineBtn;
    @FXML Button householdBtn;
    @FXML Button backBtn;
    private Stage primaryStage;
    private Scene scene;
    private Parent root;

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

    private void backToMain(ActionEvent e) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("/View/MainMenu_View.fxml"));
        primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
