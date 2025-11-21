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
        double subTotal = calculateCartSubTotal();
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
     * Is a method which checks if the desired product has enough stock/quantity in order to fulfill the customer's request.
     * @param product is the product that wants to be added to the cart
     * @param quantity is the number of products desired to be added to cart
     * @return a boolean to signify success / failure
     */
    public boolean addItem(Product_Model product, int quantity) {
        if (product.getProductQuantity() < quantity) {
            return false;
        } else {
            cart.getItems().add(new Item_Model(product, quantity));
            return true;
        }
    }

    /**
     * Method which iterates through all items in cart and checks if product that wants to be removed is part of the cart
     * @param productID is the ID of the product being removed
     * @return boolean indicating success/failure of search
     */
    public boolean removeItem(int productID) {
        for (Item_Model i : cart.getItems()) {
            if (i.getProduct().getProductID() == productID) {
                cart.getItems().remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Method which iterates through all the items in the cart to find the item being searched
     * @param id is the id of the product that wants to be found
     * @return Item that is searched
     */
    public Item_Model findItem(int id) {
        for (Item_Model i : cart.getItems()) {
            if (i.getProduct().getProductID() == id) {
                return i;
            }
        }
        return null;
    }

    /**
     * Updates the quantity of the product in cart
     * @param id id of the product that wants to be updated
     * @param amount quantity of the product
     * @return boolean, shows success or failure of the process
     */
    public boolean updateQuantity(int id, int amount) {
        for (Item_Model i : cart.getItems()) {
            if (i.getProduct().getProductID() == id && i.getProduct().getProductQuantity() >= amount) {
                i.setQuantity(amount);
                return true;
            }
        }
        return false;
    }

    /**
     * Iterates and calculates the total price for every item in the cart.
     * @return double (total price)
     */
    public double calculateCartSubTotal() {
        double subTotal = 0;
        for (Item_Model i : cart.getItems()) {
            subTotal += i.getProduct().getProductPrice() * i.getQuantity();
        }
        return subTotal;
    }
}
