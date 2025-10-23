package StoreApp;

import java.util.ArrayList;
import java.util.Scanner;

public class StoreDriver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Inventory inventory = new Inventory();
        ArrayList<Shelf> shelves = inventory.getShelves();
        Employee[] employees = {new Employee("Bob","Bob1234@gmail.com", "password123", "Manager"),
                                new Employee("Sam", "Sam1234@gmail.com", "password421", "Restocker"),
                                new Employee("Max", "Max1234@gmail.com", "password987", "Restocker")};

        // INITIALIZATION OF INVENTORY'S PRODUCTS
        // Food
        inventory.addProduct(new Product("Sandwich", 30.0, 10, "Food", "Subway", "Cheese", "2025-10-27"));
        inventory.addProduct(new Product("Pastries", 45.0, 10, "Food", "Subway", "Cheese", "2025-10-26"));
        inventory.addProduct(new Product("Fried Chicken", 50.0, 10, "Food", "Subway", "Cheese", "2026-01-15"));
        inventory.addProduct(new Product("Sushi", 120.0, 10, "Food", "Makimura Ramen Bar", "Raw", "2027-03-10"));
        inventory.addProduct(new Product("Yogurt", 215.0, 10, "Food", "Chobani", "Vanilla", "2025-10-27"));
        
        // Beverages
        inventory.addProduct(new Product("Dairy", 100.0, 10, "Beverages", "Selecta", "Milk", "2025-11-30"));
        inventory.addProduct(new Product("Coffee", 220.0, 10, "Beverages", "Starbucks", "Iced", "2025-02-02"));
        inventory.addProduct(new Product("Tea", 115.0, 10, "Beverages", "Honest Tea", "Green Tea", "2026-02-14"));
        inventory.addProduct(new Product("Energy Drink", 159.0, 10, "Beverages", "Red Bull", "The Summer Edition", "2027-10-11"));
        inventory.addProduct(new Product("Beer", 90.0, 10, "Beverages", "Stella Artois", "Stella 0.0", "2028-08-15"));
        
        // Toiletries
        inventory.addProduct(new Product("Soap", 50.0, 10, "Toiletries", "Safeguard", "Pink", null));
        inventory.addProduct(new Product("Shampoo", 99.0, 10, "Toiletries", "Head & Shoulders", "Classic Clean", null));
        inventory.addProduct(new Product("Airy Matte Tint", 30.0, 10, "Toiletries", "BLK Cosmetics", "Dearest Rose Pink", null));
        inventory.addProduct(new Product("Toothbrush", 135.0, 10, "Toiletries", "Oral-B", "Electric Toothbrush", null));
        inventory.addProduct(new Product("Deodorant", 199.0, 10, "Toiletries", "Old Spice", "Wolfthorne", null));
        
        // Cleaning Products
        inventory.addProduct(new Product("Detergent", 55.0, 10, "Cleaning Products", "Tide", "Powder", "2027-01-02"));
        inventory.addProduct(new Product("Tissue", 69.0, 10, "Cleaning Products", "Femme", "Interfolded Paper Towel", "2027-03-25"));
        inventory.addProduct(new Product("Hand Sanitizer", 95.0, 10, "Cleaning Products", "Purell", "Bottled", "2029-09-23"));
        inventory.addProduct(new Product("Degreaser", 215.0, 10, "Cleaning Products", "Lysol", "Multi-Purpose", "2028-11-11"));
        inventory.addProduct(new Product("Bleach", 150.0, 10, "Cleaning Products", "Zonrox", "Original", "2023-11-05"));
        
        // Medications
        inventory.addProduct(new Product("Adhesive Bandage", 25.0, 10, "Medications", "Band-Aid", "Waterproof", "2026-05-17"));
        inventory.addProduct(new Product("Antibiotic", 30.0, 10, "Medications", "Amoxil", "Amoxicillin", "2026-01-23"));
        inventory.addProduct(new Product("Antihistamine", 80.0, 10, "Medications", "Claritin", "Standard Tablet", null));
        inventory.addProduct(new Product("Anesthetics", 125.0, 10, "Medications", "Diprivan", "Propofol", null));
        inventory.addProduct(new Product("Paracetamol", 55.0, 10, "Medications", "Biogesic", "500mg", "2028-08-15"));

        System.out.println("\nInitialization of inventory complete.\n");

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
                            if (currentEmployee.getRole().equals("Manager"))
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

            else if (choice.equals("2") || choice.equalsIgnoreCase("Customer View"))
            {
                Cart cart = new Cart();
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
                                System.out.println("\nChecking out\n");

                                System.out.println("Enter your name: ");
                                String name = input.nextLine();

                                System.out.println("Enter your email address: ");
                                String email = input.nextLine();

                                System.out.println("Do you possess a membership card? (Yes/No)");
                                boolean hasMembership = input.nextLine().equalsIgnoreCase("yes");

                                MembershipCard membCard = null;
                                if (hasMembership)
                                {
                                    System.out.println("Enter your membership card number: ");
                                    String membCardNum = input.nextLine();
                                    membCard = new MembershipCard(membCardNum);
                                }

                                System.out.println("Are you a senior citizen? (Yes/No)");
                                boolean isSenior = input.nextLine().equalsIgnoreCase("yes");

                                Customer currentCustomer = new Customer(name, email, "", membCard, isSenior, cart);

                                Transaction transaction = new Transaction(currentCustomer, currentCustomer.getCart());

                                System.out.println("\nYour purchase for today:");
                                currentCustomer.getCart().displayCart();
                                System.out.printf("Subtotal: PHP%.2f\n", transaction.calculateSubtotal());
                                System.out.printf("Discount: PHP%.2f\n", transaction.calculateDiscount());
                                System.out.printf("Tax: PHP%.2f\n", transaction.calculateTax());
                                System.out.printf("TOTAL: PHP%.2f\n", transaction.calculateTotal());

                                System.out.println("\nEnter amount received: PHP ");
                                double amountReceived = input.nextDouble();
                                transaction.setAmountReceived(amountReceived);

                                if (amountReceived < transaction.calculateTotal())
                                {
                                    System.out.println("Insufficient amount.");
                                    return;
                                }

                                if (inventory.operateCartPurchase(currentCustomer.getCart()))
                                {
                                    System.out.printf("Change: PHP%.2f\n", transaction.calculateChange());

                                    if (currentCustomer.hasMembership())
                                    {
                                        int pointsEarned = transaction.calculateMembershipPoints();

                                        System.out.println("Points you earned: " + pointsEarned);
                                        System.out.println("Your total points: " + currentCustomer.getMembershipCard().getPoints());
                                    }
                                    else
                                    {
                                        if (!hasMembership)
                                        {
                                            System.out.println("Do you want to avail of our membership? (Yes/No)");
                                            boolean membChoice = input.nextLine().equalsIgnoreCase("yes");
    
                                            if (membChoice)
                                            {
                                                String cardNumber = membCard.generateCardNumber(); // NOTE: method not yet done
                                                membCard = new MembershipCard(cardNumber);
                                                currentCustomer.setMembershipCard(membCard);
                                            }
                                            else
                                            {
                                                System.out.println("Alright! Maybe try it next time?");
                                            }
                                        }
                                    }

                                    System.out.println("\nHere are your receipt details:\n");
                                    Receipt receipt = new Receipt(transaction);
                                    receipt.displayReceiptDetails();

                                    currentCustomer.getCart().clearCart();
                                    System.out.println("Thank you for shopping here! See you soon!");
                                }
                                else
                                {
                                    System.out.println("Your purchase failed to push through.");
                                }
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
        input.close();
    }
}
