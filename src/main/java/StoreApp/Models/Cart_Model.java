package StoreApp.Models;

import java.util.ArrayList;

public class Cart_Model {
    private ArrayList<Item> items;

    /**
     * Default constructor for class Cart
     */
    public Cart_Model() {
        this.items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
