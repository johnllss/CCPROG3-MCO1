
public class Customer extends User {
    private boolean memberShipCard;
    private boolean isSenior;
    private Cart cart;

    public Customer(String uniqueID, String name, String email, String password, boolean memberShipCard, boolean isSenior) {
        super(uniqueID, name, email, password);
        this.memberShipCard = memberShipCard;
        this.isSenior = isSenior;
        Cart cart = new Cart();
    }

    /**
     *
     * @return
     */
    public boolean isMemberShipCard() {
        return memberShipCard;
    }

    /**
     *
     * @return
     */
    public boolean isSenior() {
        return isSenior;
    }

}
