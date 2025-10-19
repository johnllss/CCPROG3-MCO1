package StoreApp;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Shelf> shelves;

    public Inventory()
    {
        this.shelves = new ArrayList<Shelf>();
        initializeShelves();
    }

    public void initializeShelves()
    {
        // TODO: Shelves initialization
        // Might need to discuss on Category being a class or not
        // Need to know if Shelves should have capacity and have only one Category of food to showcase
    }

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

        return false;
    }

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

    public boolean removeProduct(String id)
    {
        // Check each shelf
        for (Shelf shelf: shelves)
        {
            // Remove the product from the shelf
            Product isRemoved = shelf.removeProductFromShelf(id);

            if (isRemoved != null)
            {
                System.out.println("Product successfully removed.");
                return true;
            }
        }

        return false;
    }

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

    public boolean saveInventoryToFile(String fileName)
    {
        // TODO: SAVING TO FILE

        System.out.println("Saving inventory to " + fileName);

        return false;
    }

    public boolean loadInventoryFromFile(String fileName)
    {
        // TODO: LOADING FROM FILE

        System.out.println("Loading inventory from " + fileName);

        return false;
    }

    public void displayInventory()
    {
        System.out.println(">>> INVENTORY OVERVIEW <<<");

        for (Shelf shelf: shelves)
        {
            shelf.displayShelf();
        }

        System.out.printf("Total Products: %d%n", getTotalProductCount());
    }

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

    public boolean operateCartPurchase(Cart cart)
    {
        ArrayList<Item> itemsInCart = cart.getItems();

        for (Item item: itemsInCart)
        {
            Product itemInInventory = findProduct(item.getProduct().getProductID());

            itemInInventory.reduceStock(item.getQuantity());
        }

        return true;
    }

    // GETTERS
    private int getTotalProductCount()
    {
        int count = 0;

        for (Shelf shelf: shelves)
        {
            count += shelf.getProductsOnShelf().size();
        }

        return count;
    }

    public ArrayList<Shelf> getShelves()
    {
        return shelves;
    }
}
