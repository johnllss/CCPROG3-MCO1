/**
 *
 */
public class User {
    private String uniqueID;
    private String name;
    private String email;
    private String password;

    public User(String uniqueID, String name, String email, String password)
    {
        this.uniqueID = uniqueID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean login(String email, String password)
    {
        if(email.equals(this.email) && password.equals(this.password))
        {
            return true;
        }

        return false;
    }


}

