package StoreApp;

import java.util.ArrayList;

public class Shelf {
    private int shelfID;
    private static int shelfIDCounter = 0;
    private String category;
    private int currentCapacity;
    private int maxCapacity;
    private ArrayList<Product> products;

    public Shelf(String category, int maxCapacity)
    {
        this.shelfID = ++shelfIDCounter;
        this.category = category;
        this.maxCapacity = maxCapacity;
        this.products = new ArrayList<Product>();
    }

    public boolean addProductToShelf(Product product)
    {
        if (isShelfFull())
        {
            System.out.println("Oops! Shelf is full.");
            return false;
        } else if (!product.getProductCategory().equalsthis.category)
        {
            System.out.println("The product's category does not match this shelf's category!");
            return false;
        } else 
        {
            products.add(product);
            return true;
        }
    }

    public boolean isShelfFull()
    {
        return products.size() >= maxCapacity;
    }

    public Product removeProductFromShelf(String productID)
    {
        // check each product in ArrayList<Product> products
        for (Product productOnShelf: products)
        {
            // if productOnShelf's productID == productID
            if (String.valueOf(productOnShelf.getProductID()).equals(productID))
            {
                // remove the specified product by accessing the index where the for loop is at
                products.remove(products.indexOf(productOnShelf));
                return productOnShelf;
            }
        }

        return null;
    }

    public Product findProductOnShelf(int productID)
    {
        for (Product productOnShelf: products)
        {
            if (productOnShelf.getProductID() == productID)
            {
                return productOnShelf;
            }
        }

        return null;
    }

    public void displayShelf()
    {
        // SHELF INFORMATION
        // "%-10s" and others: '-' means left align, 10 means 10 spaces
        System.out.printf("\n>>> SHELF: %-10s | CATEGORY: %-20s | CAPACITY: %2d/%d <<<\n", shelfID, category, products.size(), maxCapacity);

        if (products.isEmpty())
        {
            System.out.println("Currently empty.");
        } else
        {
            // DISPLAY HEADERS FOR PRODUCTS
            System.out.printf("\n\n%-12s %-25s %-12s %-8s %-8s%n", "Product ID", "Name", "Brand", "Price", "Stock");
            System.out.println("-----");

            // ACTUAL PRODUCT INFORMATION
            for (Product productOnShelf: products)
            {
                String stockStatus = "";

                // checks for product's stock quantity
                if (productOnShelf.getProductQuantity() < 3)
                {
                    // and sets to "Low stock" if below set threshold
                    stockStatus = "Low stock.";
                }

                System.out.printf("%-12d %-25s %-12s PHP %-8.2f %-8d%s%n", 
                    productOnShelf.getProductID(), productOnShelf.getProductName(), productOnShelf.getBrand(), productOnShelf.getProductPrice(), productOnShelf.getProductQuantity(), stockStatus);
            }
        }
    }

    public int getShelfID()
    {
        return shelfID;
    }

    public String getShelfCategory()
    {
        return category;
    }

    public ArrayList<Product> getProductsOnShelf()
    {
        return products;
    }

    public int getMaxCapacity()
    {
        return maxCapacity;
    }

    public int getCurrentCapacity()
    {
        return currentCapacity;
    }

    public void setShelfCategory(String newCategory)
    {
        this.category = newCategory;
    }
}