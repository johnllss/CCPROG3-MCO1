package StoreApp;


public class Customer extends User {
    private MembershipCard membershipCard;
    private boolean isSenior;
    private int age;
    private Cart cart;

    /***
     * Class Constructor
     * @param name
     * @param email
     * @param password
     * @param membershipCard
     * @param isSenior
     */
    public Customer(String name, String email, String password, MembershipCard membershipCard, boolean isSenior) {
        super(name, email, password);
        this.membershipCard = membershipCard;
        this.isSenior = isSenior;
        this.cart = new Cart();
    }

    /***
     * method that checks if the saved customer has a membership card
     * @return boolean has membership = true null membership = false
     */
    public boolean hasMembership()
    {
        if (membershipCard == null)
        {
            return false;
        }

        return true;
    }

    /**
     * method that checks if the age inputted by customer is available for senior discount
     * @return boolean for success/failure
     */
    public boolean isSenior()
    {
        if(isSenior == true)
            {
            return true;
            }
        return false;
    }

    /***
     * Method that saves the current customer's data to a file
     * @return boolean to show if customer's data is saved to file
     */
    public boolean saveCustomerDataToFile()
    {
        // TODO implement
        return false;
    }

    /***
     * Method that loads the current customer's data to a file
     * @return boolean to show if loading is successful or not
     */
    public boolean loadCustomerDataFromFile()
    {
        // TODO implement
        return false;
    }
    
    // GETTERS AND SETTERS
    /**
     * Getter for membership card
     * @return membershipcard details for customer
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Getter for membership card
     * @return membershipcard details for customer
     */
    public MembershipCard getMembershipCard()
    {
        return membershipCard;
    }
    
    /***
     * Getter for cart
     * @return cart details for customer
     */
    public Cart getCart() 
    {
        return cart;
    }

    /**
     * Setter for attribute senior
     * @param isSenior
     */
    public void setSenior(boolean isSenior)
    {
        this.isSenior = isSenior;
    }
}
