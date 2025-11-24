package StoreApp.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import StoreApp.Models.Inventory_Model;
import StoreApp.Models.Product_Model;
import StoreApp.Models.Cart_Model;
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
     * This method is delegated the task of finding a product by ID in the Inventory.
     * @param productID is the ID of the product to find.
     * @return Product_Model if found, null otherwise.
     */
    public Product_Model findProduct(int productID)
    {
        return inventory.findProduct(productID);
    }

    /**
     * This method is delegated the task of finding a product by name and brand in the Inventory.
     * @param productName is the name of the product to find.
     * @param productBrand is the brand of the product to find.
     * @return Product_Model if found, null otherwise.
     */
    public Product_Model findProduct(String productName, String productBrand)
    {
        return inventory.findProduct(productName, productBrand);
    }

    /**
     * This method is delegated the task of adding a product to the Inventory.
     * @param product is the product to add.
     * @return boolean for success/failure.
     */
    public boolean addProduct(Product_Model product)
    {
        return inventory.addProduct(product);
    }

    /**
     * This method is delegated the task of restocking a product in the Inventory.
     * @param productID is the ID of the product to restock.
     * @param amount is the amount to add to the product's quantity.
     * @return boolean for success/failure.
     */
    public boolean restockProduct(int productID, int amount)
    {
        return inventory.restockProduct(productID, amount);
    }

    /**
     * This method is delegated the task of removing a product from the Inventory.
     * @param productID is the ID of the product to remove.
     * @return boolean for success/failure.
     */
    public boolean removeProduct(int productID)
    {
        return inventory.removeProduct(productID);
    }

    /**
     * This method is delegated the task of updating product name.
     * @param productID is the ID of the product to update.
     * @param newName is the new name for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductName(int productID, String newName)
    {
        return inventory.updateProductName(productID, newName);
    }

    /**
     * This method is delegated the task of updating product price.
     * @param productID is the ID of the product to update.
     * @param newPrice is the new price for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductPrice(int productID, double newPrice)
    {
        return inventory.updateProductPrice(productID, newPrice);
    }

    /**
     * This method is delegated the task of updating product brand.
     * @param productID is the ID of the product to update.
     * @param newBrand is the new brand for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductBrand(int productID, String newBrand)
    {
        return inventory.updateProductBrand(productID, newBrand);
    }

    /**
     * This method is delegated the task of updating product variant.
     * @param productID is the ID of the product to update.
     * @param newVariant is the new variant for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductVariant(int productID, String newVariant)
    {
        return inventory.updateProductVariant(productID, newVariant);
    }

    /**
     * This method is delegated the task of updating product expiration date.
     * @param productID is the ID of the product to update.
     * @param newExpirationDate is the new expiration date for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductExpirationDate(int productID, String newExpirationDate)
    {
        return inventory.updateProductExpirationDate(productID, newExpirationDate);
    }

    /**
     * This method is delegated the task of verifying cart stock.
     * @param cart is the cart to verify.
     * @return boolean for success/failure.
     */
    public boolean verifyCartStock(Cart_Model cart)
    {
        return inventory.verifyCartStock(cart);
    }

    /**
     * This method is delegated the task of processing cart purchase.
     * @param cart is the cart to process.
     * @return boolean for success/failure.
     */
    public boolean operateCartPurchase(Cart_Model cart)
    {
        return inventory.operateCartPurchase(cart);
    }

    /**
     * This method asks for confirmation from the employee for logging out. If confirmed, the employee will be logged out.
     * @param event is the event listener.
     */
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