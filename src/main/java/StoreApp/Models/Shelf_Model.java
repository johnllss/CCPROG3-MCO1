package StoreApp.Models;

import java.util.ArrayList;

public class Shelf_Model {
    private int shelfID;
    private static int shelfIDCounter = 0;
    private String category;
    private ArrayList<Product_Model> products;

    /**
     * Class Shelf parameterized constructor
     * @param category is the category for the Shelf
     * @param maxCapacity is the max number of Products in the Shelf
     */
    public Shelf_Model(String category, int maxCapacity) {
        this.shelfID = ++shelfIDCounter;
        this.category = category;
        this.products = new ArrayList<Product_Model>();
    }

    /**
     * This method adds a product to the Shelf.
     * @param product is the product to be added.
     */
    public void addProductToShelf(Product_Model product) {
        this.products.add(product);
    }

    /**
     * This method removes a product to the Shelf.
     * @param product is the product to be added.
     */
    public void removeProductFromShelf(Product_Model product) {
        this.products.remove(product);
    }

    /**
     * This is a getter method to get the Shelf's category.
     * @return int for category.
     */
    public String getShelfCategory() {
        return category;
    }

    /**
     * This is a getter method to get the Shelf's products.
     * @return ArrayList Product - All Shelf's Products
     */
    public ArrayList<Product_Model> getProductsOnShelf() {
        return products;
    }
}