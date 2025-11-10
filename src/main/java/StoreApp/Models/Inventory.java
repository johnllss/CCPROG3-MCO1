package StoreApp.Models;

import java.util.ArrayList;

/***
 * This is the storage for all shelves where they employees may edit or restock
 */
public class Inventory {
    private ArrayList<Shelf> shelves;

    /**
     * Default Constructor for inventory
     */
    public Inventory()
    {
        this.shelves = new ArrayList<Shelf>();
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
            shelves.add(new Shelf(c, 20));
        }
    }

    /**
     * This method finds the product based from the product ID.
     * @param productID is the product's ID
     * @return Product - All of the Product's details
     */
    public Product findProduct(int productID)
    {
        // for each shelves available, find product id and return if not null
        for (Shelf shelf: shelves)
        {
            Product product = shelf.findProductOnShelf(productID);

            if (product != null)
            {
                return product;
            }   
        }

        return null;
    }

    /***
     * This method iterates all the shelf to find product using product name and product brand
     * @param productName name of product wanting to be found
     * @param productBrand brand of product wanting to be found
     * @return product object that wants to be found can be null if not found
     */
    public Product findProduct(String productName, String productBrand)
    {
        // for each shelves available, find product id and return if not null
        for (Shelf shelf: shelves)
        {
            Product product = shelf.findProductOnShelf(productName, productBrand);

            if (product != null)
            {
                return product;
            }
        }

        return null;
    }

    /**
     * This method adds a new product to the Inventory catalogue.
     * @param product is the new product and all its details
     * @return boolean for success/failure
     */
    public boolean addProduct(Product product)
    {
        // check each of the shelves
        for (Shelf shelf: shelves)
        {
            // check if category matches 
            if (product.getProductCategory() == shelf.getShelfCategory())
            {
                // add product
                boolean isAdded = shelf.addProductToShelf(product);

                if (isAdded)
                {
                    System.out.println("Product added successfully.");
                }

                return isAdded;
            }
        }

        System.out.println("Failed to add the product.");
        return false;
    }

    /**
     * This method restocks the product's quantity based on the productID.
     * @param productID is the product's ID
     * @param amount is the amount to be added
     * @return boolean for success/failure
     */
    public boolean restockProduct(int productID, int amount)
    {
        Product product = findProduct(productID);

        // If not nonexistent, restock
        if (product != null)
        {
            product.updateStock(amount);

            System.out.println("Product successfully restocked.");

            return true;
        }
        
        return false;
    }

    /**
     * This method removes a product from Inventory based on the productID.
     * @param productID is the product's ID
     * @return boolean for success/failure
     */
    public boolean removeProduct(int productID)
    {
        // Check each shelf
        for (Shelf shelf: shelves)
        {
            // Remove the product from the shelf
            Product isRemoved = shelf.removeProductFromShelf(productID);

            if (isRemoved != null)
            {
                System.out.println("Product successfully removed.");
                return true;
            }
        }

        return false;
    }

    /**
     * This product updates the name of the product.
     * @param productID is the product's ID
     * @param newName is the new name of the product
     * @return boolean for success/failure
     */
    public boolean updateProductName(int productID, String newName)
    {
        Product product = findProduct(productID);

        if (product != null)
        {
            product.setProductName(newName);

            System.out.println("Product name successfully updated.");

            return true;
        }

        return false;
    }

    /**
     * This method finds the product that matches the productID provided and updates its price.
     * @param productID is the product's ID
     * @param newPrice is the new price to be assigned
     * @return boolean for success/failure of updating product price
     */
    public boolean updateProductPrice(int productID, double newPrice)
    {
        Product product = findProduct(productID);

        if (product != null)
        {
            product.setProductPrice(newPrice);

            System.out.println("Product price successfully updated.");

            return true;
        }

        return false;
    }

    /**
     * This method finds the product that matches the productID provided and updates its brand.
     * @param productID is the product's ID
     * @param newBrand is the new brand to be assigned
     * @return boolean for success/failure for updating product brand
     */
    public boolean updateProductBrand(int productID, String newBrand)
    {
        Product product = findProduct(productID);

        if (product != null)
        {
            product.setBrand(newBrand);

            System.out.println("Product brand successfully updated.");

            return true;
        }

        return false;
    }

    /**
     * This method finds the product that matches the productID provided and updates its variant.
     * @param productID is the product's ID
     * @param newVariant is the new variant to be assigned
     * @return boolean for success/failure updating product variant
     */
    public boolean updateProductVariant(int productID, String newVariant)
    {
        Product product = findProduct(productID);

        if (product != null)
        {
            product.setVariant(newVariant);

            System.out.println("Product variant successfully updated.");

            return true;
        }

        return false;
    }

    /**
     * This method finds the product that matches the productID provided and updates its expiration date.
     * @param productID is the product's ID
     * @param newExpirationDate is new expiration date to be assigned
     * @return boolean for success/failure updating product expiration date
     */
    public boolean updateProductExpirationDate(int productID, String newExpirationDate)
    {
        Product product = findProduct(productID);

        if (product != null)
        {
            product.setExpirationDate(newExpirationDate);

            System.out.println("Product expiration date successfully updated.");

            return true;
        }

        return false;
    }

    /**
     * This product returns all products that are low on stock.
     * @param quantityLevel is the quantity threshold level to determine low stock status.
     * @return ArrayList Product - All products that are low on stock.
     */
    public ArrayList<Product> getLowStockProducts(int quantityLevel)
    {
        // declare this to store the products with low stocks
        ArrayList<Product> lowStockProducts = new ArrayList<Product>();

        // check each shelf...
        for (Shelf shelf: shelves)
        {
            // check each product on shelf...
            for (Product product: shelf.getProductsOnShelf())
            {
                // then, check if lower than threshold
                if (product.getProductQuantity() < quantityLevel)
                {
                    lowStockProducts.add(product);
                }
            }
        }

        return lowStockProducts;
    }

    /**
     * This method gets all products that are based on the provided category.
     * @param category is the category of the products to look for.
     * @return ArrayList Product - All products that are under the specified category.
     */
    public ArrayList<Product> getProductsByCategory(String category)
    {
        // check each shelf
        for (Shelf shelf: shelves)
        {
            // if provided category is same with shelf category
            if (shelf.getShelfCategory() == category)
            {
                return shelf.getProductsOnShelf();
            }
        }

        // return empty array list?
        return new ArrayList<>();
    }

    /**
     * This method gets all products that are already expired.
     * @return ArrayList Product - All products that are already expired.
     */
    public ArrayList<Product> getExpiredProducts()
    {
        ArrayList<Product> expiredProducts = new ArrayList<Product>();

        for (Shelf shelf: shelves)
        {
            for (Product product: shelf.getProductsOnShelf())
            {
                if (product.isExpired())
                {
                    expiredProducts.add(product);
                }
            }
        }

        return expiredProducts;
    }

    /**
     * This method saves the Inventory's data to a file
     * @param fileName is the file name of the file
     * @return boolean for success/failure
     */
    public boolean saveInventoryToFile(String fileName)
    {
        // TODO: SAVING TO FILE

        System.out.println("Saving inventory to " + fileName);

        return false;
    }

    /**
     * This method loads the saved Inventory data from a file
     * @param fileName is the file name of the file
     * @return boolean for success/failure
     */
    public boolean loadInventoryFromFile(String fileName)
    {
        // TODO: LOADING FROM FILE

        System.out.println("Loading inventory from " + fileName);

        return false;
    }

    /**
     * This method displays the products in the inventory in a per Shelf basis.
     *
     */
    public void displayInventory()
    {
        System.out.println(">>> INVENTORY OVERVIEW <<<");

        for (Shelf shelf: shelves)
        {
            shelf.displayShelf();
        }

        System.out.printf("Total Products: %d%n", getTotalProductCount());
    }

    /**
     * This method verifies the stocks of the Customer's items placed inside their cart.
     * @param cart is the customer's cart.
     * @return boolean for success/failure
     */
    public boolean verifyCartStock(Cart cart)
    {
        ArrayList<Item> itemsInCart = cart.getItems();

        for (Item item: itemsInCart)
        {
            Product productItem = item.getProduct();
            int userDesiredQty = item.getQuantity();

            // find the product in inventory
            Product productItemInInventory = findProduct(productItem.getProductID());

            // check if product exists in inventory
            if (productItemInInventory == null)
            {
                System.out.println("Uh-oh! " +productItem.getProductName()+ " no longer available.");
                return false;
            }

            // check if product has sufficient stock
            if (productItemInInventory.getProductQuantity() < userDesiredQty)
            {
                System.out.println("Sadly, " +productItem.getProductName()+ " has an insufficient stock.");
                System.out.println("Your desired quantity: " +userDesiredQty);
                System.out.println("Available quantity: " +productItem.getProductQuantity());

                return false;
            }
        }

        // all items have sufficient stock
        return true;
    }

    /**
     * This method processes the Customer's purchase after being verified. This mainly reduces the stocks of the items checked out.
     * @param cart is the Customer's cart.
     * @return boolean for success/failure
     */
    public boolean operateCartPurchase(Cart cart)
    {
        ArrayList<Item> itemsInCart = cart.getItems();

        for (Item item: itemsInCart)
        {
            Product itemInInventory = findProduct(item.getProduct().getProductID());

            // reduce stock of the product item by quantity amount
            itemInInventory.reduceStock(item.getQuantity());
        }

        return true;
    }

    // GETTERS
    /**
     * This gets the total number of products in the Inventory.
     * @return int for the product count.
     */
    private int getTotalProductCount()
    {
        int count = 0;

        for (Shelf shelf: shelves)
        {
            count += shelf.getProductsOnShelf().size();
        }

        return count;
    }

    /**
     * This is a getter method to return the shelves of the Inventory.
     * @return ArrayList Shelf - All shelves in Inventory.
     */
    public ArrayList<Shelf> getShelves()
    {
        return shelves;
    }
}
