package StoreApp;

import java.util.ArrayList;

public class Shelf_View {
    public void displayFullShelf()
    {
        System.out.println("Oops! Shelf is full.");
    }

    public void displayProductCategoryMismatch(){
        System.out.println("The product's category does not match this shelf's category!");
    }

    public void displaySuccessAdding(){
        System.out.println("Successfully added this product to shelf!");
    }
    public void displaySuccessRemoving(){
        System.out.println("Successfully Removed this product from shelf!");
    }

    public void isEmpty(){
        System.out.println("Shelf is empty!");
    }

    /***
     * Displays a modified view for customers for better UI
     */
    public void displayShelfCustomerView(String category, ArrayList<Product> products)
    {
        System.out.println("\n== " + category + "==\n");
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
}
