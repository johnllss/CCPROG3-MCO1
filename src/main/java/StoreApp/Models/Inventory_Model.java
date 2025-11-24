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

    /**
     * This method finds a product by its ID across all shelves.
     * @param productID is the ID of the product to find.
     * @return Product_Model if found, null otherwise.
     */
    public Product_Model findProduct(int productID)
    {
        for (Shelf_Model shelf: shelves)
        {
            for (Product_Model product: shelf.getProductsOnShelf())
            {
                if (product.getProductID() == productID)
                {
                    return product;
                }
            }
        }

        return null;
    }

    /**
     * This method finds a product by its name and brand across all shelves.
     * @param productName is the name of the product to find.
     * @param productBrand is the brand of the product to find.
     * @return Product_Model if found, null otherwise.
     */
    public Product_Model findProduct(String productName, String productBrand)
    {
        for (Shelf_Model shelf: shelves)
        {
            for (Product_Model product: shelf.getProductsOnShelf())
            {
                if (product.getProductName().equalsIgnoreCase(productName) &&
                    product.getBrand().equalsIgnoreCase(productBrand))
                {
                    return product;
                }
            }
        }

        return null;
    }

    /**
     * This method adds a product to the appropriate shelf based on its category.
     * @param product is the product to add.
     * @return boolean for success/failure.
     */
    public boolean addProduct(Product_Model product)
    {
        if (product == null)
        {
            return false;
        }

        for (Shelf_Model shelf: shelves)
        {
            if (product.getProductCategory().equals(shelf.getShelfCategory()))
            {
                shelf.addProductToShelf(product);
                return true;
            }
        }

        return false;
    }

    /**
     * This method restocks a product by adding to its quantity.
     * @param productID is the ID of the product to restock.
     * @param amount is the amount to add to the product's quantity.
     * @return boolean for success/failure.
     */
    public boolean restockProduct(int productID, int amount)
    {
        if (amount <= 0)
        {
            return false;
        }

        Product_Model product = findProduct(productID);

        if (product != null)
        {
            product.setProductQuantity(product.getProductQuantity() + amount);
            return true;
        }

        return false;
    }

    /**
     * This method removes a product from inventory.
     * @param productID is the ID of the product to remove.
     * @return boolean for success/failure.
     */
    public boolean removeProduct(int productID)
    {
        for (Shelf_Model shelf: shelves)
        {
            for (Product_Model product: shelf.getProductsOnShelf())
            {
                if (product.getProductID() == productID)
                {
                    shelf.removeProductFromShelf(product);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This method updates a product's name.
     * @param productID is the ID of the product to update.
     * @param newName is the new name for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductName(int productID, String newName)
    {
        Product_Model product = findProduct(productID);

        if (product != null && newName != null)
        {
            product.setProductName(newName);
            return true;
        }

        return false;
    }

    /**
     * This method updates a product's price.
     * @param productID is the ID of the product to update.
     * @param newPrice is the new price for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductPrice(int productID, double newPrice)
    {
        if (newPrice < 0)
        {
            return false;
        }

        Product_Model product = findProduct(productID);

        if (product != null)
        {
            product.setProductPrice(newPrice);
            return true;
        }

        return false;
    }

    /**
     * This method updates a product's brand.
     * @param productID is the ID of the product to update.
     * @param newBrand is the new brand for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductBrand(int productID, String newBrand)
    {
        Product_Model product = findProduct(productID);

        if (product != null && newBrand != null)
        {
            product.setBrand(newBrand);
            return true;
        }

        return false;
    }

    /**
     * This method updates a product's variant.
     * @param productID is the ID of the product to update.
     * @param newVariant is the new variant for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductVariant(int productID, String newVariant)
    {
        Product_Model product = findProduct(productID);

        if (product != null && newVariant != null)
        {
            product.setVariant(newVariant);
            return true;
        }

        return false;
    }

    /**
     * This method updates a product's expiration date.
     * @param productID is the ID of the product to update.
     * @param newExpirationDate is the new expiration date for the product.
     * @return boolean for success/failure.
     */
    public boolean updateProductExpirationDate(int productID, String newExpirationDate)
    {
        Product_Model product = findProduct(productID);

        if (product != null && newExpirationDate != null)
        {
            product.setExpirationDate(newExpirationDate);
            return true;
        }

        return false;
    }

    /**
     * This method verifies that all items in a cart have sufficient stock in the Inventory.
     * @param cart is the cart to verify.
     * @return boolean for success/failure.
     */
    public boolean verifyCartStock(Cart_Model cart)
    {
        if (cart == null || cart.isEmpty())
        {
            return false;
        }

        for (Item_Model item: cart.getItems())
        {
            Product_Model productItem = item.getProduct();

            Product_Model productItemInInventory = findProduct(productItem.getProductID());

            if (productItemInInventory == null)
            {
                return false;
            }

            if (productItemInInventory.getProductQuantity() < item.getQuantity())
            {
                return false;
            }
        }

        return true;
    }

    /**
     * This method processes a cart purchase by reducing inventory quantities.
     * @param cart is the cart to process.
     * @return boolean for success/failure.
     */
    public boolean operateCartPurchase(Cart_Model cart)
    {
        if (cart == null || cart.isEmpty())
        {
            return false;
        }

        for (Item_Model item: cart.getItems())
        {
            Product_Model itemInInventory = findProduct(item.getProduct().getProductID());

            if (itemInInventory == null)
            {
                return false;
            }

            int newQuantity = itemInInventory.getProductQuantity() - item.getQuantity();

            if (newQuantity < 0)
            {
                return false;
            }

            itemInInventory.setProductQuantity(newQuantity);
        }

        return true;
    }
}