package StoreApp;

public class Transaction {
    private Cart cart;
    private Customer customer;
    private double subtotal;
    private double discount;
    private double tax;
    private double total;
    private double amountReceived;
    private double change;
    private String paymentMethod;
    private String timestamp;

    public Transaction(Customer customer, Cart cart)
    {
        this.cart = cart;
        this.customer = customer;
        this.subtotal = 0.0;
        this.discount = 0.0;
        this.tax = 0.0;
        this.total = 0.0;
        this.amountReceived = 0.0;
        this.change = 0.0;
        this.paymentMethod = "";
        this.timestamp = "";
    }

    public double calculateSubtotal()
    {

    }

    public double calculateDiscount()
    {

    }

    public double calculateTax()
    {

    }

    public double calculateTotal()
    {
        
    }

    public double calculateChange()
    {

    }

    public int calculateMembershipPoints()
    {

    }

    public boolean isDiscountable(String cardNumber, boolean isSenior)
    {

    }



    // GETTERS
    public Cart getCart()
    {
        return cart;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public double getSubtotal()
    {
        return subtotal;
    }

    public double getDiscount()
    {
        return discount;
    }

    public double getTax()
    {
        return tax;
    }

    public double getTotal()
    {
        return total;
    }

    public double getAmountReceived()
    {
        return amountReceived;
    }

    public double getChange()
    {
        return change;
    }

    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    public String getTimeStamp()
    {
        return timestamp;
    }
}
