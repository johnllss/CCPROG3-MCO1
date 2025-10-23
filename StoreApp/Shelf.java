package StoreApp;

import java.util.ArrayList;

public class Shelf {
    private int shelfID;
    private static int shelfIDCounter = 0;
    private String category;
    private int currentCapacity;
    private int maxCapacity;
    private ArrayList<Product> products;

    /**
     * Class Shelf parameterized constructor
     * @param category is the category for the Shelf
     * @param maxCapacity is the max number of Products in the Shelf
     */
    public Shelf(String category, int maxCapacity)
    {
        this.shelfID = ++shelfIDCounter;
        this.category = category;
        this.maxCapacity = maxCapacity;
        this.products = new ArrayList<Product>();
    }

    /**
     * This method adds a product to the Shelf.
     * @param product is the product to be added.
     * @return boolean for success/failure
     */
    public boolean addProductToShelf(Product product)
    {
        if (isShelfFull())
        {
            System.out.println("Oops! Shelf is full.");
            return false;
        } else if (!product.getProductCategory().equals(this.category))
        {
            System.out.println("The product's category does not match this shelf's category!");
            return false;
        } else 
        {
            products.add(product);
            return true;
        }
    }

    /**
     * This method checks if the Shelf is full or not.
     * @return boolean for success/failure
     */
    public boolean isShelfFull()
    {
        return products.size() >= maxCapacity;
    }

    /**
     * This method removes a product from the Shelf based on the productID.
     * @param productID is the product's ID to use for finding the product.
     * @return Product is the product removed and its details.
     */
    public Product removeProductFromShelf(int productID)
    {
        // check each product in ArrayList<Product> products
        for (Product productOnShelf: products)
        {
            // if productOnShelf's productID == productID
            if (productOnShelf.getProductID() == productID)
            {
                // remove the specified product by accessing the index where the for loop is at
                products.remove(products.indexOf(productOnShelf));
                return productOnShelf;
            }
        }

        return null;
    }

    /***
     * Method returns product from given productName and productBrand
     * @param productName name of the product that wants to be found
     * @param productBrand brand of the product that wants to be found
     * @return product object
     */
    public Product findProductOnShelf(String productName, String productBrand)
    {
        for(Product product: this.getProductsOnShelf()){
            if(product.getProductName().equalsIgnoreCase(productName) && product.getBrand().equalsIgnoreCase(productBrand))
            {
                return product;
            }
        }

        return null;

    }

    /***
     * Method to find product in shelf using product id
     * @param productID ID of the product wanting to be found
     * @return product object that is being found
     */
    public Product findProductOnShelf(int productID)
    {
        for(Product product: this.getProductsOnShelf()){
            if(product.getProductID() == productID)
            {
                return product;
            }

        }

        return null;

    }

    /**
     * This method displays the Products stored in a Shelf.
     * @return void
     */
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

    /***
     * Displays a modified view for customers for better UI
     */
    public void displayShelfCustomerView()
    {
        System.out.println("\n== " + this.category + "==\n");
        if (products.isEmpty())
        {
            System.out.println("Currently empty.");
        } else
        {
            // DISPLAY HEADERS FOR PRODUCTS
            System.out.printf("%-4s %-30s %-30s %-12s %-15s %-15s%n", "#", "Product Name", "Brand", "Price", "Stock", "Availability");
            System.out.println("-----");

            // ACTUAL PRODUCT INFORMATION
            int count = 1;
            for (Product productOnShelf: products)
            {
                String stockStatus = "In-Stock";

                // checks for product's stock quantity
                if (productOnShelf.getProductQuantity() < 3)
                {
                    // and sets to "Low stock" if below set threshold
                    stockStatus = "Low stock.";
                }

                System.out.printf("%-4d %-30s %-30s PHP %-9.2f %-15d %-15s%n",
                        count++, productOnShelf.getProductName(), productOnShelf.getBrand(), productOnShelf.getProductPrice(), productOnShelf.getProductQuantity(), stockStatus);
            }
        }

    }

    /**
     * This is a getter method to get the Shelf's shelfID.
     * @return int for shelfID.
     */
    public int getShelfID()
    {
        return shelfID;
    }

    /**
     * This is a getter method to get the Shelf's category.
     * @return int for category.
     */
    public String getShelfCategory()
    {
        return category;
    }

    /**
     * This is a getter method to get the Shelf's products.
     * @return ArrayList<Product> - All Shelf's Products
     */
    public ArrayList<Product> getProductsOnShelf()
    {
        return products;
    }

    /**
     * This is a getter method to get the Shelf's maxCapacity.
     * @return int for maxCapacity.
     */
    public int getMaxCapacity()
    {
        return maxCapacity;
    }

    /**
     * This is a getter method to get the Shelf's currentCapacity.
     * @return int for currentCapacity.
     */
    public int getCurrentCapacity()
    {
        return currentCapacity;
    }

    /**
     * This method sets the Shelf's category.
     * @param newCategory is the new category to be used.
     * @return void
     */
    public void setShelfCategory(String newCategory)
    {
        this.category = newCategory;
    }
}