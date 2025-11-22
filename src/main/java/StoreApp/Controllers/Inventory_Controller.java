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

    public Product_Model findProduct(int productID)
    {
        for (Shelf_Model shelf: inventory.getShelves())
        {
            for (Product_Model product: shelf.getProductsOnShelf())
            {
                if (product.getProductID() == productID)
                {
                    return product;
                }
            }
        }

        return null;
    }

    public Product_Model findProduct(String productName, String productBrand)
    {
        for (Shelf_Model shelf: inventory.getShelves())
        {
            for (Product_Model product: shelf.getProductsOnShelf())
            {
                if (product.getProductName().equalsIgnoreCase(productName) &&
                    product.getBrand().equalsIgnoreCase(productBrand))
                {
                    return product;
                }
            }
        }

        return null;
    }

    public boolean addProduct(Product_Model product)
    {
        if (product == null)
        {
            return false;
        }

        for (Shelf_Model shelf: inventory.getShelves())
        {
            if (product.getProductCategory().equals(shelf.getShelfCategory()))
            {
                shelf.addProduct(product);
                return true;
            }
        }

        return false;
    }

    public boolean restockProduct(int productID, int amount)
    {
        if (amount <= 0)
        {
            return false;
        }

        Product_Model product = findProduct(productID);

        if (product != null)
        {
            product.setProductQuantity(product.getProductQuantity() + amount);
            return true;
        }

        return false;
    }

    public boolean removeProduct(int productID)
    {
        for (Shelf_Model shelf: inventory.getShelves())
        {
            for (Product_Model product: shelf.getProductsOnShelf())
            {
                if (product.getProductID() == productID)
                {
                    shelf.removeProduct(product);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean updateProductName(int productID, String newName)
    {
        Product_Model product = findProduct(productID);

        if (product != null && newName != null)
        {
            product.setProductName(newName);
            return true;
        }

        return false;
    }

    public boolean updateProductPrice(int productID, double newPrice)
    {
        if (newPrice < 0)
        {
            return false;
        }

        Product_Model product = findProduct(productID);

        if (product != null)
        {
            product.setProductPrice(newPrice);
            return true;
        }

        return false;
    }

    public boolean updateProductBrand(int productID, String newBrand)
    {
        Product_Model product = findProduct(productID);

        if (product != null && newBrand != null)
        {
            product.setBrand(newBrand);
            return true;
        }

        return false;
    }

    public boolean updateProductVariant(int productID, String newVariant)
    {
        Product_Model product = findProduct(productID);

        if (product != null && newVariant != null)
        {
            product.setVariant(newVariant);
            return true;
        }

        return false;
    }

    public boolean updateProductExpirationDate(int productID, String newExpirationDate)
    {
        Product_Model product = findProduct(productID);

        if (product != null && newExpirationDate != null)
        {
            product.setExpirationDate(newExpirationDate);
            return true;
        }

        return false;
    }

    public boolean verifyCartStock(Cart_Model cart)
    {
        if (cart == null || cart.isEmpty())
        {
            return false;
        }

        for (Item_Model item: cart.getItems())
        {
            Product_Model productItem = item.getProduct();

            Product_Model productItemInInventory = findProduct(productItem.getProductID());

            if (productItemInInventory == null)
            {
                return false;
            }

            if (productItemInInventory.getProductQuantity() < item.getQuantity())
            {
                return false;
            }
        }

        return true;
    }

    public boolean operateCartPurchase(Cart_Model cart)
    {
        if (cart == null || cart.isEmpty())
        {
            return false;
        }

        for (Item_Model item: cart.getItems())
        {
            Product_Model itemInInventory = findProduct(item.getProduct().getProductID());

            if (itemInInventory == null)
            {
                return false;
            }

            int newQuantity = itemInInventory.getProductQuantity() - item.getQuantity();

            if (newQuantity < 0)
            {
                return false;
            }

            itemInInventory.setProductQuantity(newQuantity);
        }

        return true;
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
