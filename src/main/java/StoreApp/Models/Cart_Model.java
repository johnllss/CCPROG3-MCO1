package StoreApp.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart_Model {
    private ObservableList<Item_Model> items;

    /**
     * Default constructor for class Cart
     */
    public Cart_Model() {
        this.items = FXCollections.observableArrayList();
    }

    /**
     * This is a getter method to get Cart's items.
     * @return ObservableList of Item_Model for items.
     */
    public ObservableList<Item_Model> getItems() {
        return items;
    }

    /**
     * Method that checks if the cart is empty
     * @return boolean to show if empty = true or not empty = false
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Method that removes every item in the cart
     */
    public void clearCart() {
        items.clear();
    }

    /**
     * This method checks if the desired product has enough stock/quantity in order to fulfill the customer's request.
     * @param product is the product that is to be added.
     * @param quantity is the number of products desired to be added to cart.
     * @return a boolean to signify success/failure.
     */
    public boolean addItem(Product_Model product, int quantity) {
        for(Item_Model item : items) {
            if (item.getProduct().getProductID() == product.getProductID()) {
                // Already in cart → just ignore (or use updateQuantity instead)
                return updateQuantity(product, quantity);
            }
        }
        if(product.ProductQuantity(quantity)) {
            items.add(new Item_Model(product, quantity));
            return true;
        } else {
            return false;
        }
    }


    /**
     * This method iterates through all items in cart and checks if product that wants to be is to be removed is in the cart.
     * @param productID is the ID of the product being removed.
     * @return boolean indicating success/failure of search
     */
    public boolean removeItem(int productID) {
        for (Item_Model i : items) {
            if (i.getProduct().getProductID() == productID) {
                items.remove(i);
                return true;
            }
        }

        return false;
    }

    /**
     * This method iterates through all the items in the cart to find the item being searched.
     * @param productID is the id of the product that wants to be found.
     * @return Item_Model that is searched.
     */
    public Item_Model findItem(int productID) {
        for (Item_Model item: items) {
            if (item.getProduct().getProductID() == productID) {
                return item;
            }
        }

        return null;
    }

    /**
     * This method updates the quantity of the product in cart.
     * @param product is the ID of the product to be updated.
     * @param amount quantity of the product.
     * @return boolean, shows success or failure of the process.
     */
    public boolean updateQuantity(Product_Model product, int amount) {
        for(Item_Model item: items) {
            if(item.getProduct().getProductID() == product.getProductID()) {
                if(item.getProduct().ProductQuantity(amount)) {
                    item.setQuantity(amount);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * This method iterates and calculates the total price for every item in the cart.
     * @return double (total price)
     */
    public double calculateCartSubTotal() {
        double subTotal = 0;

        for (Item_Model item: items) {
            subTotal += item.calculateItemSubtotal();
        }

        return subTotal;
    }
}
