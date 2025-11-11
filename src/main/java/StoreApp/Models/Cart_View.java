package StoreApp;

import java.util.ArrayList;

public class Cart_View {
    /***
     * iterates through every item and displays product name, product price, product quantity
     */
    public void displayCart(ArrayList<Item> items)
    {
        System.out.printf("%-20s %-20s %-15s %-10s%n",
                "Product Name", "Product Brand", "Product Price", "Quantity");
        System.out.println("--------------------------------------------------------------------------");
        for(Item i : items)
        {
            System.out.printf("%-20s %-20s %-15.2f %-10d%n",
                    i.getProduct().getProductName(),
                    i.getProduct().getBrand(),
                    i.getProduct().getProductPrice(),
                    i.getQuantity());

            System.out.println(" ");
        }
    }
}
