package StoreApp;

public class Discount {
    private long discountNumber;

    public Discount(long discountNumber)
    {
        this.discountNumber = discountNumber;
    }

    public boolean applySeniorDiscount() {
        // TODO implement calculation

        return false;
    }

    public boolean applyMembershipDiscount() {
        // TODO implement calculation

        return false;
    }

    public long getDiscountNumber()
    {
        return discountNumber;
    }
}
