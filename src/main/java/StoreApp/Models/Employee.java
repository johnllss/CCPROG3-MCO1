package StoreApp.Models;

/***
 * This represents the employee of the store where they have access to do the restocking or certain
 * processes only employees can do
 */
public class Employee extends User {
    private String role;

    /**
     * Default constructor for employee
     */
    public Employee()
    {
        super();
        this.role = "N/A";
    }

    /**
     * Class Employee parameterized constructor
     * @param name is the employee's name
     * @param email is the employee's email
     * @param password is the employee's password
     * @param role is the employee's role
     */
    public Employee(String name, String email, String password, String role) {
        super(name, email, password);
        this.role = role;
    }

    /**
     * This method logs the employee in to be able to manage the inventory.
     * @param email is the employee's email
     * @param password is the employee's password
     * @return boolean for success/failure
     */
    public boolean login(String email, String password)
    {
        if(email.equals(this.getEmail()) && password.equals(this.getPassword()))
        {
            return true;
        }

        return false;
    }

    /**
     * This method restocks a specific product from the inventory with a specific quantity
     * @param inventory is the inventory
     * @param productID is the ID of the product
     * @param quantity is the quantity to add
     */
    public void restock(Inventory inventory, int productID, int quantity)
    {
        // TODO do restock()
        inventory.restockProduct(productID, quantity);
    }

    /**
     * Getter method to get Employee's role
     * @return String to get employee's role
     */
    public String getRole()
    {
        return role;
    }

    /**
     * This method sets the new role of the employee.
     * @param newRole is the new role to set to employee's
     */
    public void setRole(String newRole)
    {
        this.role = newRole;
    }
}
