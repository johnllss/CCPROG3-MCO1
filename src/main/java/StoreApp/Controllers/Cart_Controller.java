package StoreApp.Controllers;

import StoreApp.Models.Cart_Model;
import StoreApp.Models.Customer_Model;
import StoreApp.Models.Item_Model;
import StoreApp.Models.Product_Model;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javafx.scene.control.Label;

import static javafx.collections.FXCollections.*;

public class Cart_Controller {
    @FXML private VBox productsBox;
    @FXML private Label subtotalLabel;
    @FXML private Label vatLabel;
    @FXML private Label totalLabel;
    @FXML private TableView<Item_Model> cartTable;
    @FXML private TableColumn<Cart_Model, Integer> productQuantityColumn;
    @FXML private TableColumn<Cart_Model, String> productNameColumn;
    @FXML private TableColumn<Cart_Model, String> productPriceColumn;
    private Customer_Model customer;
    private Cart_Model cart;
    private ObservableList<Item_Model> ItemList;


    public void SetObjects(Customer_Model customer){
        this.customer = customer;
        this.cart = customer.getCart();
        this.ItemList = observableArrayList(cart.getItems());
        cartTable.setItems(ItemList);
<<<<<<< Updated upstream

=======
        /*
        updateProductBoxDisplay();
        updateAllTotals();

         */
>>>>>>> Stashed changes
    }

    @FXML
    public void initialize()
    {
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
        double subTotal = cart.calculateCartSubTotal();
        double vat = subTotal * 0.10; // TODO: modify to include seniority validation
        double total = subTotal + vat;

        subtotalLabel.setText(String.format("₱ %.2f", subTotal));
        vatLabel.setText(String.format("₱ %.2f", vat));
        totalLabel.setText(String.format("₱ %.2f", total));
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

    /**
     * This method is delegated the task of adding an item to the cart model.
     * @param product is the product that is to be added to the cart.
     * @param quantity is the number of products desired to be added to cart.
     * @return boolean to signify success/failure.
     */
    public boolean addItem(Product_Model product, int quantity) {
        return customer.getCart().addItem(product, quantity);
    }

    /**
     * This method is delegated the task of removing an item to the cart model.
     * @param productID is the ID of the product being removed.
     * @return boolean indicating success/failure of search.
     */
    public boolean removeItem(int productID) {
        return cart.removeItem(productID);
    }

    /**
     * This method is delegated the task of finding an item in the cart.
     * @param productID is the id of the product that needs to be found.
     * @return Item_Model that is searched.
     */
    public Item_Model findItem(int productID) {
        return cart.findItem(productID);
    }

    /**
     * This method is delegated the task of updating quantity of the item in the cart.
     * @param product id of the product that needs to be updated.
     * @param amount quantity of the product.
     * @return boolean, shows success or failure of the process.
     */
    public boolean updateQuantity(Product_Model product, int amount) {
        return cart.updateQuantity(product, amount);
    }
}
