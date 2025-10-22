package StoreApp;

public class Discount {
    private long discountNumber;

    /**
     * Class Discount parameterized constructor
     * @param discountNumber 
     */
    public Discount(long discountNumber)
    {
        this.discountNumber = discountNumber;
    }

    /**
     * This method applies the discount if the user is a senior.
     * @return boolean for success/failure
     */
    public boolean applySeniorDiscount() {
        // TODO implement calculation

        return false;
    }

    /**
     * This method applies the discount if the user has a membership.
     * @return boolean for success/failure
     */
    public boolean applyMembershipDiscount() {
        // TODO implement calculation

        return false;
    }

    /**
     * A getter method to get discount number
     * @return long for the discount number
     */
    public long getDiscountNumber()
    {
        return discountNumber;
    }
}
