package StoreApp.Models;

public class User_Model {
    private int uniqueID;
    private static int uniqueIDCounter = 0;
    private String name;
    private String email;
    private String password;

    /**
     * Class User default constructor
     */
    public User_Model()
    {
        // pre-increment so first instance is int 1
        this.uniqueID = ++uniqueIDCounter;

        this.name = "N/A";
        this.email = "N/A";
        this.password = "N/A";
    }

    /**
     * Class User_Model parameterized constructor
     * @param name is the user's name.
     * @param email is the user's email.
     * @param password is the user's password.
     */
    public User_Model(String name, String email, String password){
        this.uniqueID = ++uniqueIDCounter;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * This is a getter method to get User's uniqueID.
     * @return int for uniqueID.
     */
    public int getUniqueID() {
        return uniqueID;
    }

    /**
     * This is a getter method to get User's name.
     * @return String for name.
     */
    public String getName() {
        return name;
    }

    /**
     * This sets User's name.
     * @param name name given by user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This is a getter method to get User's email.
     * @return String for email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * This sets User's email
     * @param email email provided by user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This is a getter method to get User's password.
     * @return String for password.
     */
    public String getPassword() {
        return password;
    }
}
