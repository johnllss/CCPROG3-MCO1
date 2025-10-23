package StoreApp;

import java.util.ArrayList;
import java.util.Scanner;


public class StoreDriver {
    private static Scanner input;
    private static Inventory inventory;
    private static ArrayList<Shelf> shelves = inventory.getShelves();
    private static Employee[] employees;

    public static void main(String args[]) {
        input = new Scanner(System.in);
        inventory = new Inventory();
        shelves = inventory.getShelves();
        employees = new Employee[] {new Employee("Bob","Bob1234@gmail.com", "password123", "Manager"),
                                    new Employee("Sam", "Sam1234@gmail.com", "password421", "Restocker"),
                                    new Employee("Max", "Max1234@gmail.com", "password987", "Restocker")};

        boolean MainLoop = true;

        while (MainLoop)
        {
            System.out.println("==Welcome to the Convenience Store==");
            System.out.println("1. Manage Inventory");
            System.out.println("2. Customer View");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            String choice = input.nextLine();

            if (choice.equals("1") || choice.equalsIgnoreCase("Manage Inventory"))
            {
                System.out.println("\n==Manage Inventory==\n");
                System.out.println("Enter employee email: ");
                String email = input.nextLine();
                System.out.println("Enter employee password: ");
                String password = input.nextLine();

                boolean success = false;
                Employee currentEmployee = null;

                for (Employee employee: employees)
                {
                    if(employee.login(email, password))
                    {
                        success = true;
                        currentEmployee = employee;
                        break;
                    }
                }

                if (!success)
                {
                    System.out.println("Invalid email or password");
                }
                else 
                {
                    System.out.println("\nSuccessfully logged in!\n");

                    boolean employeeManaging = true;

                    while (employeeManaging)
                    {
                        System.out.println("==Inventory Management==");
                        System.out.println("1. Restock Shelf");
                        System.out.println("2. Add Product");
                        System.out.println("3. Remove Product");
                        System.out.println("4. Return to Main Menu");
                        System.out.print("Enter your choice: ");
                        String var1 = input.nextLine();

                        if (var1.equals("1") || var1.equalsIgnoreCase("Restock Shelf"))
                        {
                            if (currentEmployee.getRole().equals("Restocker") || currentEmployee.getRole().equals("Manager"))
                            {
                                System.out.println("\nRestocking\n");

                                System.out.println("Enter Product ID: ");
                                int id = input.nextInt();
                                System.out.print("Enter restock quantity: ");
                                int quantity = input.nextInt();
                                input.nextLine();

                                currentEmployee.restock(inventory, id, quantity);
                            }
                        }
                        else if (var1.equals("2") || var1.equalsIgnoreCase("Add Product"))
                        {
                            System.out.println("\nAdding New Product\n");

                            System.out.println("Enter Product Name: ");
                            String name = input.nextLine();
                            System.out.println("Enter Product Price: ");
                            double price = input.nextDouble();
                            System.out.println("Enter Product Quantity: ");
                            int quantity = input.nextInt();
                            System.out.println("Enter Product Category: ");
                            String category = input.nextLine();
                            System.out.println("Enter Product Brand: ");
                            String brand = input.nextLine();
                            System.out.println("Enter Product Variant: ");
                            String variant = input.nextLine();
                            System.out.println("Enter Product Expiration Date (Type '0' for none): ");
                            String expirationDate = input.nextLine();
                            if (expirationDate.equalsIgnoreCase("0"))
                            {
                                expirationDate = null;
                            }

                            Product product = new Product(name, price, quantity, category, brand, variant, expirationDate);

                            if (inventory.addProduct(product))
                            {
                                System.out.println("Product has been added.");
                            }
                            else
                            {
                                System.out.println("Failed to add the product.");
                            }
                        }
                        else if (var1.equals("3") || var1.equalsIgnoreCase("Remove Product"))
                        {
                            System.out.println("\nRemoving Product\n");

                            System.out.println("Enter Product ID:");
                            int productID = input.nextInt();

                            if (inventory.removeProduct(productID))
                            {
                                System.out.println("Product has been removed.");
                            }
                            else
                            {
                                System.out.println("Product cannot be found.");
                            }
                        }
                        else if (var1.equals("4") || var1.equalsIgnoreCase("Return to Main Menu"))
                        {
                            System.out.println("Thank you for managing the inventory!");
                            employeeManaging = false;
                        }
                    }
                }
            }

            //TODO Customer View//
            else if (choice.equals("2") || choice.equalsIgnoreCase("Customer View"))
            {
                Cart  cart = new Cart();
                System.out.println("\nCustomer View\n");

                boolean customerView = true;
                while (customerView) 
                {
                    int currentIndex = 0;

                    while (currentIndex < shelves.size()) 
                    {
                        Shelf currentShelf = shelves.get(currentIndex);

                        System.out.println("== Category ==" + currentShelf.getShelfCategory() + "Shelf: " + currentIndex + 1);

                        currentShelf.displayShelfCustomerView();

                        System.out.println("\nThe Shopping Menu\n");
                        System.out.println("1. Add Product to Cart");
                        System.out.println("2. Next Shelf");
                        System.out.println("3. View Cart");
                        System.out.println("4. Remove Item From Cart");
                        System.out.println("5. Clear Cart");
                        System.out.println("6. Checkout Cart");
                        System.out.println("7. Exit");
                        System.out.print("Enter your choice: ");
                        String var1 = input.nextLine();

                        if (var1.equals("1") || var1.equalsIgnoreCase("Add Product to Cart"))
                        {
                            System.out.print("Enter Product Name: ");
                            String productName = input.nextLine();
                            System.out.println("Enter Product Brand: ");
                            String productBrand = input.nextLine();
                            System.out.println("Enter Quantity: ");
                            int qty = input.nextInt();
                            input.nextLine();
                            for(Shelf shelf: shelves){
                                if(shelf.findProductOnShelf(productName, productBrand)!=null){
                                    cart.addItem(shelf.findProductOnShelf(productName, productBrand), qty);
                                }
                                else{
                                    System.out.println("Product not found");
                                }
                            }

                        }
                        else if (var1.equals("2") || var1.equalsIgnoreCase("Next Shelf"))
                        {
                            currentIndex++;
                        }
                        else if (var1.equals("3") || var1.equalsIgnoreCase("View Cart"))
                        {
                            System.out.println("\nYour cart right now:\n");
                            cart.displayCart();
                        }
                        else if (var1.equals("4") || var1.equalsIgnoreCase("Remove Item From Cart"))
                        {
                            // System.out.println("Enter item Name:");
                            // String itemName = input.nextLine();
                            // System.out.println("Enter item Brand:");
                            // String itemBrand = input.nextLine();

                            System.out.println("Enter item's product ID: ");
                            int itemProductID = input.nextInt();

                            if (cart.removeItem(itemProductID))
                            {
                                System.out.println("Product has been removed from your cart.");
                            }
                            else
                            {
                                System.out.println("Product is not found in your cart.");
                            }
                        }
                        else if (var1.equals("5") || var1.equalsIgnoreCase("Clear Cart"))
                        {
                            System.out.println("\nYour cart has been cleared.\n");
                            cart.clearCart();
                        }
                        else if (var1.equals("6") || var1.equalsIgnoreCase("Checkout Cart"))
                        {
                            if (!inventory.verifyCartStock(cart))
                            {
                                System.out.println("Sorry! Some items in your cart are already out of stock.");
                                return;
                            }
                            else
                            {
                                Transaction transaction = new Transaction(null, cart);

                                
                            }
                        }
                        else if (var1.equals("7") || var1.equalsIgnoreCase("Exit"))
                        {
                            System.out.println("Thank you for checking out the convenience store!");
                            customerView = false;
                        }

                    }
                }

            }

            else if (input.nextLine().equals("3") || input.nextLine().equalsIgnoreCase("Exit"))
            {
                System.out.println("Closing the convenience store...");
                MainLoop = false;
            }
        }
    }
}
