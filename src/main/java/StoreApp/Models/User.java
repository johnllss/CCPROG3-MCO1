package StoreApp.Models;

/***
 * Stores the necessary information for all the users
 * parent class of employee and customer
 *
 */

public class User {
    private int uniqueID;
    private static int uniqueIDCounter = 0;
    private String name;
    private String email;
    private String password;

    /**
     * Class User default constructor
     */
    public User() 
    {
        // pre-increment so first instance is int 1
        this.uniqueID = ++uniqueIDCounter;
        
        this.name = "N/A";
        this.email = "N/A";
        this.password = "N/A";
    }

    /**
     * Class User parameterized constructor
     * @param name name provided by user to login
     * @param email email provided by user to login should be unique
     * @param password provided by user to login should be unique
     */
    public User(String name, String email, String password)
    {
        this.uniqueID = ++uniqueIDCounter;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    /**
     * This is a getter method to get User's uniqueID.
     * @return int for uniqueID.
     */
    public int getUniqueID()
    {
        return uniqueID;
    }

    /**
     * This is a getter method to get User's name.
     * @return String for name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * This is a getter method to get User's password.
     * @return String for password.
     */
    public String getPassword(){
        return password;
    }

    /**
     * This is a getter method to get User's email.
     * @return String for email.
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * This sets User's name.
     * @param name name given by user
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * This sets User's email
     * @param email email provided by user
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * This sets User's uniqueID
     * @param id Unique ID wanted to be set/changed into
     */
    public void setUniqueID(int id)
    {
        this.uniqueID = id;
    }
}

