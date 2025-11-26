package StoreApp.Models;

public class Employee_Model extends User_Model {
    private String role;

    /**
     * Class Employee_Model default constructor
     */
    public Employee_Model() {
        super();
        this.role = "N/A";
    }

    /**
     * Class Employee_Model parameterized constructor
     * @param name is the employee's name.
     * @param email is the employee's email.
     * @param password is the employee's password.
     * @param role is the employee's role.
     */
    public Employee_Model(String name, String email, String password, String role)
    {
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
     * This is a getter method to get Employee's role.
     * @return String for role.
     */
    public String getRole() {
        return role;
    }

    /**
     * This sets Employee's role.
     * @param role is the role to be assigned.
     */
    public void setRole(String role) {
        this.role = role;
    }
}
