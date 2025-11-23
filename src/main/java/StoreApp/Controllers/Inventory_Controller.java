package StoreApp.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import StoreApp.Models.Inventory_Model;
import StoreApp.Models.Product_Model;
import StoreApp.Models.Shelf_Model;
import StoreApp.Models.Cart_Model;
import StoreApp.Models.Item_Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Inventory_Controller implements Initializable {

    @FXML private ChoiceBox<String> choiceBox;
    @FXML private Button logout_btn;
    @FXML private AnchorPane scenePane;

    private Inventory_Model inventory;
    private Stage stage;

    private String[] categories = {"food", "beverage", "medicine", "household"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(categories);
    }

    public void getCategory(ActionEvent event)
    {
        String choice = choiceBox.getValue();
    }

    public void setInventory(Inventory_Model inv)
    {
        this.inventory = inv;
    }

    /**
     * Delegates finding a product by ID to the inventory model.
     * @param productID is the ID of the product to find.
     * @return Product_Model if found, null otherwise.
     */
    public Product_Model findProduct(int productID)
    {
        return inventory.findProduct(productID);
    }

    /**
     * Delegates finding a product by name and brand to the inventory model.
     * @param productName is the name of the product to find.
     * @param productBrand is the brand of the product to find.
     * @return Product_Model if found, null otherwise.
     */
    public Product_Model findProduct(String productName, String productBrand)
    {
        return inventory.findProduct(productName, productBrand);
    }

    /**
     * Delegates adding a product to the inventory model.
     * @param product is the product to add.
     * @return boolean for success/failure.
     */
    public boolean addProduct(Product_Model product)
    {
        return inventory.addProduct(product);
    }

    /**
     * Delegates restocking a product to the inventory model.
     * @param productID is the ID of the product to restock.
     * @param amount is the amount to add to the product's quantity.
     * @return boolean for success/failure.
     */
    public boolean restockProduct(int productID, int amount)
    {
        return inventory.restockProduct(productID, amount);
    }

    /**
     * Delegates removing a product to the inventory model.
     * @param productID is the ID of the product to remove.
     * @return boolean for success/failure.
     */
    public boolean removeProduct(int productID)
    {
        return inventory.removeProduct(productID);
    }

    /**
     * Delegates updating product name to the inventory model.
     * @param productID is the ID of the product to update.
     * @param newName is the new name for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductName(int productID, String newName)
    {
        return inventory.updateProductName(productID, newName);
    }

    /**
     * Delegates updating product price to the inventory model.
     * @param productID is the ID of the product to update.
     * @param newPrice is the new price for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductPrice(int productID, double newPrice)
    {
        return inventory.updateProductPrice(productID, newPrice);
    }

    /**
     * Delegates updating product brand to the inventory model.
     * @param productID is the ID of the product to update.
     * @param newBrand is the new brand for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductBrand(int productID, String newBrand)
    {
        return inventory.updateProductBrand(productID, newBrand);
    }

    /**
     * Delegates updating product variant to the inventory model.
     * @param productID is the ID of the product to update.
     * @param newVariant is the new variant for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductVariant(int productID, String newVariant)
    {
        return inventory.updateProductVariant(productID, newVariant);
    }

    /**
     * Delegates updating product expiration date to the inventory model.
     * @param productID is the ID of the product to update.
     * @param newExpirationDate is the new expiration date for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductExpirationDate(int productID, String newExpirationDate)
    {
        return inventory.updateProductExpirationDate(productID, newExpirationDate);
    }

    /**
     * Delegates verifying cart stock to the inventory model.
     * @param cart is the cart to verify.
     * @return boolean for success/failure.
     */
    public boolean verifyCartStock(Cart_Model cart)
    {
        return inventory.verifyCartStock(cart);
    }

    /**
     * Delegates processing cart purchase to the inventory model.
     * @param cart is the cart to process.
     * @return boolean for success/failure.
     */
    public boolean operateCartPurchase(Cart_Model cart)
    {
        return inventory.operateCartPurchase(cart);
    }

    public void logout(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout.");
        alert.setContentText("Do you want to save before exiting?");

        if (alert.showAndWait().get() == ButtonType.OK)
        {
            stage = (Stage)scenePane.getScene().getWindow();
            System.out.println("You successfully logged out!");
            stage.close();
        }
    }
}
