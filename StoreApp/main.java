import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Employee[] employees;
        while(true){
            System.out.println("==Welcome to the Conveinience Store==");
            System.out.println("1. Manage Inventory");
            System.out.println("2. Customer View");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = input.nextLine();
            if(choice.equals("1")||choice.equalsignorecase("Manage Inventory")){
                System.out.println("Enter email: ");
                String email = input.nextLine();
                System.out.println("Enter password: ");
                String password = input.nextLine();
                boolean success = false;
                Employee currentEmployee = null;
                for(Employee employee: employees){
                    if(employee.login(email, password))
                    {
                        success = true;
                        currentEmployee = employee;
                        break;
                    }
                }

                if(!success){
                    System.out.println("Invalid email or password");
                }
                else {
                    System.out.println("Successfully logged in");
                    System.out.println("==Inventory Management==");
                    System.out.println("1. Restock Shelf");
                    //To-do//
                    System.out.println("2. Add Product");
                    //To-do//
                    System.out.println("3. Remove Product");
                    System.out.print("Enter your choice: ");
                    String var1 = input.nextLine();
                    if(var1.equals("1") || var1.equalsIgnoreCase("Restock Shelf")){
                        if(currentEmployee.getRole().equals("Restocker") || currentEmployee.getRole().equals("Manager")){
                            System.out.println("Enter restock quantity: ")
                            int quantity = input.nextInt();
                            System.out.println("Enter ID: ");
                            String id = input.nextLine();
                            restock(id, quantity);
                        }
                    }

                    //To-do//

                }
            }
            if(input.nextLine().equals("2")||input.nextLine().equalsignorecase("Customer View")){

            }
        }
    }
}