package StoreApp.Models;

import java.util.ArrayList;

public class Shelf_Model {
    private int shelfID;
    private static int shelfIDCounter = 0;
    private String category;
    private int currentCapacity;
    private int maxCapacity;
    private ArrayList<Product_Model> products;

    /**
     * Class Shelf parameterized constructor
     * @param category is the category for the Shelf
     * @param maxCapacity is the max number of Products in the Shelf
     */
    public Shelf_Model(String category, int maxCapacity) {
        this.shelfID = ++shelfIDCounter;
        this.category = category;
        this.maxCapacity = maxCapacity;
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
     * This is a getter method to get the Shelf's shelfID.
     * @return int for shelfID.
     */
    public int getShelfID() {
        return shelfID;
    }

    /**
     * This method sets the Shelf's ID to a given ID.
     * @param shelfID is the new given shelfID
     */
    public void setShelfID(int shelfID) {
        this.shelfID = shelfID;
    }

    /**
     * This is a getter method to get the Shelf's category.
     * @return int for category.
     */
    public String getShelfCategory() {
        return category;
    }

    /**
     * This method sets the Shelf's category.
     * @param newCategory is the new category to be used.
     *
     */
    public void setShelfCategory(String category) {
        this.category = category;
    }

    /**
     * This is a getter method to get the Shelf's currentCapacity.
     * @return int for currentCapacity.
     */
    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    /**
     * This is a getter method to get the Shelf's maxCapacity.
     * @return int for maxCapacity.
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * This is a getter method to get the Shelf's products.
     * @return ArrayList Product - All Shelf's Products
     */
    public ArrayList<Product_Model> getProductsOnShelf() {
        return products;
    }

    public void setProducts(ArrayList<Product_Model> products) {
        this.products = products;
    }
}