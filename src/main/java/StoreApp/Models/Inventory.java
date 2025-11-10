package StoreApp.Models;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

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
        if (product == null)
        {
            return false;
        }

        // check each of the shelves
        for (Shelf shelf: shelves)
        {
            // check if category matches
            if (product.getProductCategory().equals(shelf.getShelfCategory()))
            {
                // add product
                return shelf.addProductToShelf(product);
            }
        }

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
        // prevent processing 0 or negative amounts
        if (amount <= 0)
        {
            return false;
        }

        Product product = findProduct(productID);

        // If not nonexistent, restock
        if (product != null)
        {
            product.updateStock(amount);

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

        if (product != null && newName != null)
        {
            product.setProductName(newName);

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
        // prevent processing 0 or negative newPrice
        if (newPrice < 0)
        {
            return false;
        }

        Product product = findProduct(productID);

        if (product != null)
        {
            product.setProductPrice(newPrice);
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

        if (product != null && newbrand != null)
        {
            product.setBrand(newBrand);
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

        if (product != null && newVariant != null)
        {
            product.setVariant(newVariant);
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

        if (product != null && newExpirationDate != null)
        {
            product.setExpirationDate(newExpirationDate);
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
                // then, check if lower than a given threshold
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
        if (category == null)
        {
            return new ArrayList<>();
        }

        // check each shelf
        for (Shelf shelf: shelves)
        {
            // if provided category is same with shelf category
            if (shelf.getShelfCategory().equals(category))
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
     * This method verifies the stocks of the Customer's items placed inside their cart.
     * @param cart is the customer's cart.
     * @return boolean for success/failure
     */
    public boolean verifyCartStock(Cart cart)
    {
        if (cart == null || cart.isEmpty())
        {
            return false;
        }

        for (Item item: cart.getItems())
        {
            Product productItem = item.getProduct();

            // find the product in inventory
            Product productItemInInventory = findProduct(productItem.getProductID());

            // check if product exists in inventory
            if (productItemInInventory == null)
            {
                return false;
            }

            // check if product has sufficient stock
            if (productItemInInventory.getProductQuantity() < item.getQuantity())
            {
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
        if (cart == null || cart.isEmpty())
        {
            return false;
        }

        for (Item item: cart.getItems())
        {
            Product itemInInventory = findProduct(item.getProduct().getProductID());

            if (itemInInventory == null)
            {
                return false;
            }

            // reduce stock of the product item by quantity amount
            boolean isReduced = itemInInventory.reduceStock(item.getQuantity());

            if (!isReduced)
            {
                return false;
            }
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

    /**
     * This method saves the Inventory's data to a file
     * @param fileName is the file name of the file
     * @return boolean for success/failure
     */
    public boolean saveInventoryToFile(String fileName)
    {
        // TODO: SAVING TO FILE

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

        return false;
    }
}
