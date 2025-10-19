package StoreApp;

public class Customer extends User {
    private MembershipCard memberShipCard;
    private boolean isSenior;
    private Cart cart;

    public Customer(String name, String email, String password, MembershipCard memberShipCard, boolean isSenior) {
        super(name, email, password);
        this.memberShipCard = memberShipCard;
        this.isSenior = isSenior;
        Cart cart = new Cart();
    }

    // public boolean hasMembership()
    // {

    // }

    /**
     *
     * @return
     */
    public boolean isSenior()
    {
        return this.isSenior;
    }

    // public boolean saveCustomerDataToFile()
    // {

    // }

    // public loadCustomerDataFromFile()
    // {

    // }

    // Getters and setters
    /**
     *
     * @return
     */
    public MembershipCard getMembershipCard() 
    {
        return memberShipCard;
    }

    public Cart getCart() 
    {
        return cart;
    }
}
