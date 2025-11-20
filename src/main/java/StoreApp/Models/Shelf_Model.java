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
    public Shelf_Model(String category, int maxCapacity)
    {
        this.shelfID = ++shelfIDCounter;
        this.category = category;
        this.maxCapacity = maxCapacity;
        this.products = new ArrayList<Product_Model>();
    }
    public int getShelfID() {
        return shelfID;
    }
    public void setShelfID(int shelfID) {
        this.shelfID = shelfID;
    }
    public String getShelfCategory() {
        return category;
    }
    public void setShelfCategory(String category) {
        this.category = category;
    }
    public int getCurrentCapacity() {
        return currentCapacity;
    }
    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    public ArrayList<Product_Model> getProductsOnShelf() {
        return products;
    }
    public void setProducts(ArrayList<Product_Model> products) {
        this.products = products;
    }
    public void addProduct(Product_Model product)
    {
        this.products.add(product);
    }
    public void removeProduct(Product_Model product)
    {
        this.products.remove(product);
    }

}
