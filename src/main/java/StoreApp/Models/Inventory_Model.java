package StoreApp.Models;

import java.util.ArrayList;

public class Inventory_Model {
    private ArrayList<Shelf_Model> shelves;

    /**
     * Default Constructor for inventory
     */
    public Inventory_Model()
    {
        this.shelves = new ArrayList<Shelf_Model>();
        initializeShelves();
    }

    /**
     * This method initializes each shelf of the Inventory
     *
     */
    public void initializeShelves()
    {
        String[] categories = {"Food", "Beverages", "Toiletries", "Cleaning Products", "Medications"};

        for (String c: categories)
        {
            shelves.add(new Shelf_Model(c, 20));
        }
    }

    public ArrayList<Shelf_Model> getShelves() {
        return shelves;
    }

    public ArrayList<Product_Model> getProductsByCategory(String category)
    {
        if (category == null)
        {
            return new ArrayList<>();
        }

        for (Shelf_Model shelf: shelves)
        {
            if (shelf.getShelfCategory().equals(category))
            {
                return shelf.getProductsOnShelf();
            }
        }

        return new ArrayList<>();
    }

    public ArrayList<Product_Model> getLowStockProducts(int quantityLevel)
    {
        ArrayList<Product_Model> lowStockProducts = new ArrayList<Product_Model>();

        for (Shelf_Model shelf: shelves)
        {
            for (Product_Model product: shelf.getProductsOnShelf())
            {
                if (product.getProductQuantity() < quantityLevel)
                {
                    lowStockProducts.add(product);
                }
            }
        }

        return lowStockProducts;
    }

    public ArrayList<Product_Model> getExpiredProducts()
    {
        ArrayList<Product_Model> expiredProducts = new ArrayList<Product_Model>();

        for (Shelf_Model shelf: shelves)
        {
            for (Product_Model product: shelf.getProductsOnShelf())
            {
                if (product.getExpirationDate() != null && !product.getExpirationDate().equalsIgnoreCase("N/A"))
                {
                    expiredProducts.add(product);
                }
            }
        }

        return expiredProducts;
    }

}
