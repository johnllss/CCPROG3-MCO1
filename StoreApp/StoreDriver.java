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

                if(!success)
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
                        // TODO
                        System.out.println("2. Add Product");
                        // TODO
                        System.out.println("3. Remove Product");
                        System.out.print("Enter your choice: ");
                        String var1 = input.nextLine();

                        if (var1.equals("1") || var1.equalsIgnoreCase("Restock Shelf"))
                        {
                            if (currentEmployee.getRole().equals("Restocker") || currentEmployee.getRole().equals("Manager"))
                            {
                                System.out.println("Enter Product ID: ");
                                int id = input.nextInt();
                                System.out.print("Enter restock quantity: ");
                                int quantity = input.nextInt();
                                input.nextLine();
                                currentEmployee.restock(inventory, id, quantity);
                            }
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
                        else if(var1.equals("2") || var1.equalsIgnoreCase("Next Shelf")){
                            currentIndex++;
                        }
                        else if(var1.equals("3") || var1.equalsIgnoreCase("View Cart")){
                            cart.displayCart();
                        }
                        else if(var1.equals("4") || var1.equalsIgnoreCase("Remove Product From Cart")){
                            System.out.println("Enter Product Name:");
                            String productName = input.nextLine();
                            System.out.println("Enter Product Brand:");
                            String productBrand = input.nextLine();


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
}
