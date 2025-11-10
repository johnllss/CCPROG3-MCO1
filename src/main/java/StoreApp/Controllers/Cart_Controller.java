package StoreApp.Controllers;

import StoreApp.Models.Item;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.lang.classfile.Label;

public class Cart_Controller {
    @FXML private VBox productsBox;
    @FXML private Label subtotalLabel;
    @FXML private Label vatLabel;
    @FXML private Label totalLabel;

    private ObservableList<Item> items = FXCollections.observableArrayList();

    @FXML
    public void initialize()
    {
        // TODO
        updateProductBoxDisplay();
        updateAllTotals();
    }

    private void updateProductBoxDisplay()
    {
        productsBox.getChildren().clear(); // clear existing products

        // TODO
        /*
            1. loop through each item in cart and update the VBox
            Note: might need an Item_View.java that displays the specific cart items as shown in the Canva
         */
    }

    private void updateAllTotals()
    {
        double subTotal = 0; // TODO: modify to calculate/get the subtotal
        double vat = subTotal * 0.10; // TODO: modify to include seniority validation
        double total = subtotal + vat;

        subtotalLabel.setText(String.format("PHP %.2f", subTotal));
        vatLabel.setText(String.format("₱ %.2f", vat));
        totalLabel.setText(String.format("PHP %.2f", total));
    }

    @FXML
    private void processCheckout()
    {
        System.out.println("Checkout");
        // TODO implement checkout or reroute to a .fxml checkout and make checkout controller
    }

    @FXML
    private void returnToProducts()
    {
        System.out.println("Returning");
        // TODO load back to fxml of product listings
    }
}
