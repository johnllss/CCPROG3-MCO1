package StoreApp.Models;

import java.util.ArrayList;

public class Cart_Model {
    private ArrayList<Item_Model> items;

    /**
     * Default constructor for class Cart
     */
    public Cart_Model() {
        this.items = new ArrayList<>();
    }

    public ArrayList<Item_Model> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item_Model> items) {
        this.items = items;
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
}
