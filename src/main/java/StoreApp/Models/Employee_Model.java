package StoreApp.Models;

public class Employee_Model extends User_Model {
    private String role;

    public Employee_Model() {
        super();
        this.role = "N/A";
    }

    public Employee_Model(String name, String email, String password, String role)
    {
        super(name, email, password);
        this.role = "N/A";
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
