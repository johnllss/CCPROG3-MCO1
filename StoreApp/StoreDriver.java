package StoreApp;

import java.util.ArrayList;
import java.util.Scanner;

public class StoreDriver {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        Inventory inventory = new Inventory();
        ArrayList<Shelf> shelves = inventory.getShelves();
        Employee[] employees = {new Employee("Bob","Bob1234@gmail.com", "password123", "Manager"),
                                new Employee("Sam", "Sam1234@gmail.com", "password421", "Restocker"),
                                new Employee("Max", "Max1234@gmail.com", "password987", "Restocker")};

        boolean MainLoop = true;

        while (MainLoop == true)
        {
            System.out.println("==Welcome to the Convenience Store==");
            System.out.println("1. Manage Inventory");
            System.out.println("2. Customer View");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            String choice = input.nextLine();

            if (choice.equals("1") | choice.equalsIgnoreCase("Manage Inventory"))
            {
                System.out.println("Enter email: ");
                String email = input.nextLine();
                System.out.println("Enter password: ");
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

                if(!success)
                {
                    System.out.println("Invalid email or password");
                }
                else 
                {
                    System.out.println("Successfully logged in");
                    System.out.println("==Inventory Management==");
                    System.out.println("1. Restock Shelf");
                    // TODO
                    System.out.println("2. Add Product");
                    // TODO
                    System.out.println("3. Remove Product");
                    System.out.print("Enter your choice: ");
                    String var1 = input.nextLine();
                    if(var1.equals("1") || var1.equalsIgnoreCase("Restock Shelf")){
                        if(currentEmployee.getRole().equals("Restocker") || currentEmployee.getRole().equals("Manager")){
                            System.out.print("Enter restock quantity: ");
                            int quantity = input.nextInt();
                            System.out.println("Enter ID: ");
                            int id = input.nextInt();
                            input.nextLine();
                            currentEmployee.restock(inventory, id, quantity);
                        }
                    }

                    // TODO

                }
            }

            //TODO Customer View//
            else if (choice.equals("2") || choice.equalsIgnoreCase("Customer View"))
            {
                Cart  cart = new Cart();
                boolean customerView = true;
                System.out.println("Welcome to the Convenience Store!");
                while(customerView) {
                    int currentIndex = 0;
                    while (currentIndex < shelves.size()) {
                        Shelf currentShelf = shelves.get(currentIndex);
                        System.out.println("== Category ==" + currentShelf.getShelfCategory() + "Shelf: " + currentIndex + 1);
                        currentShelf.displayShelfCustomerView();
                        System.out.println("1. Add Product to Cart");
                        System.out.println("2. Next Shelf");
                        System.out.println("3. View Cart");
                        System.out.println("4. Remove Product From Cart");
                        System.out.println("5. Clear Cart");
                        System.out.println("6. Checkout Cart");
                        System.out.println("7. Exit");
                        System.out.print("Enter your choice: ");
                        String var1 = input.nextLine();
                        if(var1.equals("1") || var1.equalsIgnoreCase("Add Product to Cart")){

                        }
                        else if(var1.equals("2") || var1.equalsIgnoreCase("Next Shelf")){
                            currentIndex++;
                        }
                        else if(var1.equals("3") || var1.equalsIgnoreCase("View Cart")){
                            cart.displayCart();
                        }
                        else if(var1.equals("4") || var1.equalsIgnoreCase("Remove Product From Cart")){

                        }
                        else if(var1.equals("5") || var1.equalsIgnoreCase("Clear Cart")){
                            cart.clearCart();
                        }
                        else if(var1.equals("6") || var1.equalsIgnoreCase("Checkout Cart")){

                        }

                        else if(var1.equals("7") || var1.equalsIgnoreCase("Exit")){
                            customerView = false;
                        }

                    }
                }

            }

            else if (input.nextLine().equals("3") || input.nextLine().equalsIgnoreCase("Exit"))
            {
                MainLoop = false;
            }
        }
    }

    public void openConvenienceStore()
    {

    }

    public void handleCustomer()
    {

    }

    public void handleInventoryManagement()
    {

    }

    private void addProduct()
    {

    }

    private void restockProduct()
    {

    }

    private void removeProduct()
    {

    }

    private void updateProduct()
    {

    }

    private void displayExpiredProducts()
    {
        
    }
}
